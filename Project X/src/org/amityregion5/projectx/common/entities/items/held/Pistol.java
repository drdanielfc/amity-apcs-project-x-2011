/**
 * Copyright (c) 2011 Amity AP CS A Students of 2010-2011.
 *
 * ex: set filetype=java expandtab tabstop=4 shiftwidth=4 :
 *
 * This program is free software: you can redistribute it and/or
 * modify it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of
 * the License, or (at your option) any later version.
 *
 * This code is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * This code is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published
 * by the Free Software Foundation.
 */
package org.amityregion5.projectx.common.entities.items.held;

import java.awt.image.BufferedImage;
import org.amityregion5.projectx.common.entities.items.Upgradeable;
import org.amityregion5.projectx.common.tools.ImageHandler;
import org.amityregion5.projectx.common.tools.Sound;

/**
 * The "stock" gun.
 * @author Joe Stein
 */

public class Pistol extends Gun implements Upgradeable {

    private static final long serialVersionUID = 606L;
    
    private final int UPGRADE_COST = 50;
    private static final BufferedImage image = ImageHandler.loadImage("Pistol");

    public Pistol()
    {
        super(600, -1, -1, 4, 8, 10);
    }

    @Override
    public Sound getSound()
    {
        return Sound.PISTOL_SHOT;
    }

    @Override
    public BufferedImage getDefaultImage()
    {
        return image;
    }

    @Override
    public String getName()
    {
        return "Magnum";
    }

    public void upgrade()
    {
        upgradeLevel++;
        setDamage(getDamage() + 2);
        setRPM(getRPM() + 2);
    }

    public int getUpgradeCost()
    {
        return UPGRADE_COST;
    }
}
