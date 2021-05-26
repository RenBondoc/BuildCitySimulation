package com.mad.cityassignment;

import java.io.Serializable;

/* NOTE: Some methods in this class file is not owned/created by me. This was provided by Curtin's COMP2008
 * Worksheet 3.
 *
 * I do not own some of this code as well as the drawable.xml used in this class file
 */

public class MapElement implements Serializable
{
    private final boolean buildable;
    private final int terrainNorthWest;
    private final int terrainSouthWest;
    private final int terrainNorthEast;
    private final int terrainSouthEast;
    private Structure structure;

    public MapElement(boolean buildable, int northWest, int northEast,
                      int southWest, int southEast, Structure structure)
    {
        this.buildable = buildable;
        this.terrainNorthWest = northWest;
        this.terrainNorthEast = northEast;
        this.terrainSouthWest = southWest;
        this.terrainSouthEast = southEast;
        this.structure = structure;
    }

    public boolean isBuildable()
    {
        return buildable;
    }

    public int getNorthWest()
    {
        return terrainNorthWest;
    }

    public int getSouthWest()
    {
        return terrainSouthWest;
    }

    public int getNorthEast()
    {
        return terrainNorthEast;
    }

    public int getSouthEast()
    {
        return terrainSouthEast;
    }

    public Structure getStructure()
    {
        return structure;
    }

    public void setStructure(Structure structure)
    {
        this.structure = structure;
    }
}
