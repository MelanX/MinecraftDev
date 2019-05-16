/*
 * Minecraft Dev for IntelliJ
 *
 * https://minecraftdev.org
 *
 * Copyright (c) 2018 minecraft-dev
 *
 * MIT License
 */

package com.demonwav.mcdev.i18n.reference

import com.demonwav.mcdev.i18n.lang.gen.psi.LangEntry
import com.demonwav.mcdev.i18n.lang.gen.psi.LangTypes
import com.demonwav.mcdev.i18n.translations.Translation
import com.intellij.openapi.util.TextRange
import com.intellij.patterns.PlatformPatterns
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiReference
import com.intellij.psi.PsiReferenceContributor
import com.intellij.psi.PsiReferenceProvider
import com.intellij.psi.PsiReferenceRegistrar
import com.intellij.util.ProcessingContext

class I18nLangReferenceContributor : PsiReferenceContributor() {
    override fun registerReferenceProviders(registrar: PsiReferenceRegistrar) {
        registrar.registerReferenceProvider(
            PlatformPatterns.psiElement().withChild(PlatformPatterns.psiElement().withElementType(LangTypes.KEY)),
            object : PsiReferenceProvider() {
                override fun getReferencesByElement(element: PsiElement, context: ProcessingContext): Array<PsiReference> {
                    val entry = element as LangEntry
                    return arrayOf(
                        I18nReference(
                            element,
                            TextRange(0, entry.key.length),
                            Translation.Key("", entry.key, "")
                        )
                    )
                }
            }
        )
    }
}