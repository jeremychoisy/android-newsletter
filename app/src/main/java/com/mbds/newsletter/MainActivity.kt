package com.mbds.newsletter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager.OnBackStackChangedListener
import androidx.lifecycle.lifecycleScope
import com.mbds.newsletter.data.database.dao.ArticleDao
import com.mbds.newsletter.data.database.db.ArticleDatabase
import com.mbds.newsletter.data.models.Article
import com.mbds.newsletter.fragments.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), OnBackStackChangedListener {
    private var menu: Menu? = null
    private var currentArticleDetails: Article? = null
    private lateinit var articleDAO: ArticleDao
    private lateinit var listOfFavoriteArticles: List<Article>
    private var hasDaoActionBeenDone: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        application.setTheme(android.R.style.Theme_Light)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Initialize dao
        val articleDB = ArticleDatabase.getInstance(applicationContext)
        articleDAO = articleDB.articleDao()
        articleDAO.getAll().observe(this, {
            listOfFavoriteArticles = it
            updateFavoriteIcon()
        })

        // Initialize toolbar
        setSupportActionBar(findViewById(R.id.toolbar))

        // Add listener in order to display back button only when needed
        supportFragmentManager.addOnBackStackChangedListener(this)
        changeFragment(HomeFragment())
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        this.menu = menu

        // Initialize the favorite button status since the onCreateOptionsMenu is called after the onCreate one.
        displayFavorite()

        return super.onCreateOptionsMenu(menu)
    }

    /**
     * Called When you press a button in the layout.
     * Permit to switch the current fragment.
     */
    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_show_favorite -> {
            changeFragment(ListOfFavoriteArticlesFragment())
            true
        }
        R.id.action_settings -> {
            changeFragment(SettingsFragment())
            true
        }
        R.id.action_about_us -> {
            changeFragment(AboutUsFragment())
            true
        }
        R.id.action_set_favorite -> {
            lifecycleScope.launch(Dispatchers.IO) {
                val fixedCurrentArticle = currentArticleDetails
                if (fixedCurrentArticle != null) {
                    if (listOfFavoriteArticles.contains(currentArticleDetails)) {
                        articleDAO.delete(fixedCurrentArticle)
                    }
                    else
                        articleDAO.insert(fixedCurrentArticle)
                }
            }
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }

    }

    /**
     * Called when the back stack changes, meaning the displayed fragment has changed.
     * Updates the toolbar with appropriate features / actions
     */
    override fun onBackStackChanged() {
        displayHomeUp()
        displayFavorite()
        displaySetFavorite()
        displaySubtitle()
    }

    /**
     * Called when the toolbar's back button is pressed.
     */
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    /**
     * Changes the current displayed [Fragment]
     * @param fragment
     */
    fun changeFragment(fragment: Fragment) {
        currentArticleDetails = if (fragment is ArticleDetailsFragment ) fragment.getCurrentArticle() else null

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container, fragment)
            addToBackStack(null)
        }.commit()
    }

    /**
     * Displays the back button is the number of fragments in the back stack
     * is superior to 1, meaning the displayed fragment is not the home fragment.
     */
    private fun displayHomeUp() {
        val canBack = supportFragmentManager.backStackEntryCount > 1;
        supportActionBar?.setDisplayHomeAsUpEnabled(canBack);
    }

    /**
     * Displays the action to reach the list of favorite articles
     * only while displaying the home fragment and the list of articles fragment.
     */
    private fun displayFavorite() {
        val currentFragment = supportFragmentManager.findFragmentById(R.id.fragment_container)
        val favoriteItemMenu = menu?.findItem(R.id.action_show_favorite)
        if (favoriteItemMenu != null) {
            when (currentFragment) {
                is CategoriesFragment -> favoriteItemMenu.isVisible = true
                is ListOfArticlesFragment -> favoriteItemMenu.isVisible = true
                else -> favoriteItemMenu.isVisible = false
            }
        }
    }

    /**
     * Displays the action to add or remove a favorite from the list of favorite articles
     * only while displaying the article details fragment.
     */
    private fun displaySetFavorite() {
        val currentFragment = supportFragmentManager.findFragmentById(R.id.fragment_container)
        val setFavoriteItemMenu = menu?.findItem(R.id.action_set_favorite)
        if (setFavoriteItemMenu != null) {
            setFavoriteItemMenu.isVisible = currentFragment is ArticleDetailsFragment
        }
    }

    /**
     * Displays the appropriate toolbar's subtitle depending on the current displayed fragment
     */
    private fun displaySubtitle() {
        val currentFragment = supportFragmentManager.findFragmentById(R.id.fragment_container)
        when (currentFragment) {
            is HomeFragment -> supportActionBar?.subtitle = getString(R.string.home_subtitle)
            is CategoriesFragment -> supportActionBar?.subtitle =
                getString(R.string.categories_subtitle)
            is ListOfFavoriteArticlesFragment -> supportActionBar?.subtitle =
                getString(R.string.favorites_subtitle)
            is ListOfArticlesFragment -> supportActionBar?.subtitle =
                getString(R.string.articles_subtitle)
            is ArticleDetailsFragment -> supportActionBar?.subtitle =
                getString(R.string.article_detail)
            is SettingsFragment -> supportActionBar?.subtitle =
                getString(R.string.settings_subtitle)
            is AboutUsFragment -> supportActionBar?.subtitle = getString(R.string.about_us_subtitle)
        }
    }

    private fun updateFavoriteIcon() {
        var favoriteItem = menu?.findItem(R.id.action_set_favorite)

        if (favoriteItem != null) {
            favoriteItem.icon = if (listOfFavoriteArticles.contains(currentArticleDetails))
                getDrawable(R.drawable.ic_favorite_black_48dp)
            else
                getDrawable(R.drawable.ic_favorite_border_black_48dp)
        }
    }
}