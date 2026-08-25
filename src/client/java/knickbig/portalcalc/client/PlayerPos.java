package knickbig.portalcalc.client;

import net.minecraft.client.player.LocalPlayer;

public class PlayerPos {
    public LocalPlayer player;
    public double XPos;
    public double YPos;
    public double ZPos;

    public PlayerPos(LocalPlayer player) {
        this.player = player;
        this.XPos = player.getX();
        this.YPos = player.getY();
        this.ZPos = player.getZ();
    }
}
