/*
 * Minecraft Dev for IntelliJ
 *
 * https://minecraftdev.org
 *
 * Copyright (c) 2017 minecraft-dev
 *
 * MIT License
 */

package com.demonwav.mcdev.platform.bukkit

import com.demonwav.mcdev.MinecraftSettings
import com.demonwav.mcdev.facet.MinecraftFacet
import com.intellij.ide.FileIconProvider
import com.intellij.openapi.module.ModuleUtilCore
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import javax.swing.Icon

class BukkitFileIconProvider : FileIconProvider {

    override fun getIcon(file: VirtualFile, flags: Int, project: Project?): Icon? {
        if (!MinecraftSettings.instance.isShowProjectPlatformIcons) {
            return null
        }

        project ?: return null

        val module = ModuleUtilCore.findModuleForFile(file, project) ?: return null
        val bukkitModule = MinecraftFacet.getInstance<BukkitModule<*>>(module, BukkitModuleType, SpigotModuleType, PaperModuleType) ?:
            return null

        if (file == bukkitModule.pluginYml) {
            return bukkitModule.icon
        }
        return null
    }
}