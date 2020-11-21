package com.mbds.newsletter.data.interfaces

import com.mbds.newsletter.data.models.Editor

interface EditorService {
    fun getEditors(): List<Editor>
}