/*
 *
 *  *
 *  *  * MobCoins - Earn coins for killing mobs.
 *  *  * Copyright (C) 2018 Max Berkelmans AKA LemmoTresto
 *  *  *
 *  *  * This program is free software: you can redistribute it and/or modify
 *  *  * it under the terms of the GNU General Public License as published by
 *  *  * the Free Software Foundation, either version 3 of the License, or
 *  *  * (at your option) any later version.
 *  *  *
 *  *  * This program is distributed in the hope that it will be useful,
 *  *  * but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  *  * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 *  *  * GNU General Public License for more details.
 *  *  *
 *  *  * You should have received a copy of the GNU General Public License
 *  *  * along with this program. If not, see <http://www.gnu.org/licenses/>.
 *  *
 *
 */

package me.max.lemonmobcoins.bukkit.messages;

import me.max.lemonmobcoins.common.utils.FileUtil;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

public class MessageManager {

    public MessageManager(){
        throw new UnsupportedOperationException("Instantiation of this class is not allowed.");

    }

    public static void load(File dataFolder, Logger logger){
        File file = new File(dataFolder, "messages.yml");
        if (!file.exists()) {
            try {
                FileUtil.saveResource("messages.yml", dataFolder, "messages.yml");
            } catch (IOException e) {
                logger.severe("Could not load messages! Stopping plugin!");
                e.printStackTrace();
                Bukkit.getPluginManager().disablePlugin(Bukkit.getPluginManager().getPlugin("LemonMobCoins"));
            }
        }

        YamlConfiguration messages = YamlConfiguration.loadConfiguration(file);
        for (String key : messages.getKeys(false)){
            Messages.valueOf(key).setMessage(messages.getString(key));
        }
    }

}
