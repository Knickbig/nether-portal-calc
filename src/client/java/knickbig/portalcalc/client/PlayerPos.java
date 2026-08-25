package knickbig.portalcalc.client;

import net.minecraft.client.player.LocalPlayer;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;

import java.awt.*;

public class PlayerPos {
    public LocalPlayer localPlayer;
    public ResourceKey<Level> dimension;
    public double XPos;
    public double YPos;
    public double ZPos;

    public PlayerPos(LocalPlayer player) {
        this.localPlayer = player;
        this.dimension = player.level().dimension();
        this.XPos = player.getX();
        this.YPos = player.getY();
        this.ZPos = player.getZ();
    }
}
