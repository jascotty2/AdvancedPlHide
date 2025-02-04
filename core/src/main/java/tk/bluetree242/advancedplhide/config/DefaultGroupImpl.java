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

package tk.bluetree242.advancedplhide.config;

import java.util.ArrayList;
import java.util.List;

public class DefaultGroupImpl implements Config.Group {
    protected DefaultGroupImpl() {
    }

    @Override
    public List<String> commands() {
        List<String> result = new ArrayList<>();
        result.add("ver");
        result.add("version");
        result.add("plugins");
        result.add("bukkit:*");
        result.add("about");
        result.add("?");
        result.add("minecraft:?");
        result.add("serverlistplus");
        result.add("pl");
        result.add("plugins");
        result.add("example1");
        result.add("example2");
        return result;
    }

    @Override
    public List<String> tabcomplete() {
        List<String> result = new ArrayList<>();
        result.add("serverlistplus");
        result.add("pl");
        result.add("plugins");
        result.add("version");
        result.add("example1");
        result.add("example2");
        return result;
    }

}
