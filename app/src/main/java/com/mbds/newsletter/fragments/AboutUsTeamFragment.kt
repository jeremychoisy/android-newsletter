package com.mbds.newsletter.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mbds.newsletter.R
import com.mbds.newsletter.adapters.CountriesAdapter
import com.mbds.newsletter.adapters.PersonAdapter
import com.mbds.newsletter.data.models.Person

/**
 * A simple [Fragment] subclass.
 */
class AboutUsTeamFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.about_us_team, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = view.findViewById<RecyclerView>(R.id.about_us_recyclerView)
        val adapterRecycler = PersonAdapter(getAllContributors(), this)
        recyclerView.layoutManager = LinearLayoutManager(view.context)
        recyclerView.adapter = adapterRecycler
    }

    fun getAllContributors(): List<Person> {

        val baroukhYoni: Person = Person(
            "Yoni Baroukh",
            "Yoni is an back-end developer in apprenticeship at Atos. Most used language : PHP",
            "https://media-exp1.licdn.com/dms/image/C5603AQGNzD6x-s_PoA/profile-displayphoto-shrink_800_800/0?e=1611187200&v=beta&t=Gev8MIkhm-d4yzuRzaNlN3sLEWDz4XDMzjIDPC0xb2Y",
            "https://www.linkedin.com/in/yonibaroukh/"
        )

        val choisyJeremy: Person = Person(
            "Jérémy Choisy",
            "Jérémy is an front-end developer in apprenticeship at Amadeus. Most used language : TypeScript",
            "https://media-exp1.licdn.com/dms/image/C4D03AQGHEv99xHHwQg/profile-displayphoto-shrink_800_800/0/1541112758484?e=1611187200&v=beta&t=wSCQ_iuWtO0uh1lN87yW3IIHf08uvX3XC9-Mzexm6w8",
            "https://www.linkedin.com/in/j%C3%A9r%C3%A9mychoisy/"
        )

        val dsgAndre: Person = Person(
            "André Da Silva Goncalves",
            "André is an back-end developer in apprenticeship at Koedia. Most used language : Java",
            "https://media-exp1.licdn.com/dms/image/C4E03AQFd1TtQy-7wIQ/profile-displayphoto-shrink_200_200/0?e=1611187200&v=beta&t=Q8J5p6jhHloZ7Lw-TqpCA6FyDj4tpmTnz6WD2GT9QF0",
            "https://www.linkedin.com/in/andr%C3%A9-da-silva-goncalves/"
        )

        val panzeraAlexis: Person = Person(
            "Alexis Panzera",
            "Alexis is an front-end developer in apprenticeship at Azur Tech Concept. Most used language : TypeScript",
            "https://media-exp1.licdn.com/dms/image/C4D03AQGRY-TWetgteQ/profile-displayphoto-shrink_800_800/0/1586505822202?e=1611187200&v=beta&t=f0ZGTHXp3qAEmwhHyS4OM7wVQjUvI5BY3iQYJZqtVr4",
            "https://www.linkedin.com/in/alexis-panzera-89b23a143/"
        )

        return listOf(baroukhYoni, choisyJeremy, dsgAndre, panzeraAlexis)
    }
}