/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.amityregion5.projectx.server.game.enemies;

import java.util.ArrayList;

import org.amityregion5.projectx.common.entities.characters.enemies.Enemy;
/**
 * A wave of enemies, consisting of several EnemyGroups.
 * @author Michael Wenke
 */
public class EnemyWave 
{
    private int waveNumber;
    private ArrayList<EnemyGroup> enemies;
    private long spawnTime;
    private final double SPAWNTIME_DIFFICULTY_RAMP = .75;
    private final double NUM_ENEMIES_DIFFICULTY_RAMP = 1.5;
    private final double ENEMY_HEALTH_DIFFICULTY_RAMP = 1.5;

    public EnemyWave(int n, ArrayList<EnemyGroup> en)
    {
        waveNumber = n;
        enemies = en;
        spawnTime = 500; //Random spawn time
    }   

    public long getSpawnTime()
    {
        return spawnTime;
    }

    public void setSpawnTime(long time)
    {
        spawnTime = time;
    }

    public int getWaveNumber()
    {
        return waveNumber;
    }

    public ArrayList<EnemyGroup> getEnemyGroups()
    {
        return enemies;
    }

    public EnemyWave nextWave()
    {
        ArrayList<EnemyGroup> newEnemies = new ArrayList<EnemyGroup>();
        for(int i = 0; i < enemies.size(); i++)
        {   
            EnemyGroup group = enemies.get(i);
            Enemy oldEnemy = group.getEnemy();
            Enemy newEnemy = new Enemy((int)(oldEnemy.getMaxHp()* ENEMY_HEALTH_DIFFICULTY_RAMP),0,0);
            EnemyGroup newGroup = new EnemyGroup(newEnemy, (int)(group.getNumEnemies() * NUM_ENEMIES_DIFFICULTY_RAMP));
            newEnemies.add(newGroup);
        }
        EnemyWave nextWave = new EnemyWave(this.getWaveNumber()+1, newEnemies);
        nextWave.setSpawnTime(this.spawnTime * (long)SPAWNTIME_DIFFICULTY_RAMP);
        return nextWave;

    }

}
