/*
 *  LICENSE
 * AdvancedPlHide
 * -------------
 * Copyright (C) 2021 - 2022 BlueTree242
 * -------------
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 *  END
 */

package tk.bluetree242.advancedplhide.utils;

import tk.bluetree242.advancedplhide.PlatformPlugin;

public class Constants {
    public static final String DEFAULT_UP_TO_DATE = "&aYou are up to date";
    public static final String DEFAULT_BEHIND = "&cYou are {versions} Behind. Please Update ASAP. Download from {download}";
    public static final String WHITELIST_MODE_PERMISSION = "plhide.whitelist-mode";
    public static final String BYPASS_PREFIX_CLEAR = "plhide.bypass.prefixes";

    public static String startupMessage() {
        return "" +
                "\n[]=====[&2Enabling AdvancedPlHide&r]=====[]\n" +
                "| &cInformation:\n&r" +
                "|   &cName: &rAdvancedPlHide\n&r" +
                "|   &cDevelopers: &rBlueTree242\n&r" +
                "|   &cVersion: &r" + PlatformPlugin.get().getCurrentVersion() + "\n&r" +
                (!PlatformPlugin.get().getCurrentBuild().equals("NONE") ? "|   &cBuild Number: &r#" + PlatformPlugin.get().getCurrentBuild() + "\n&r" : "") +
                "|   &cRunning on: &r" + PlatformPlugin.get().getType().getName() + "\n&r" +
                "| &cSupport:\n&r" +
                "|   &cGithub: &rhttps://github.com/BlueTree242/AdvancedPlHide/issues\n" +
                "|   &cDiscord: &rhttps://advancedplhide.ml/support\n" +
                "[]================================[]";
    }
}
