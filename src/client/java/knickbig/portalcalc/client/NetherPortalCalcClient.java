package knickbig.portalcalc.client;
import net.minecraft.world.level.Level;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;

public class NetherPortalCalcClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
            dispatcher.register(
                    ClientCommandManager.literal("CalcPortal")
                            .executes(context -> {
                                var player = Minecraft.getInstance().player;
                                PlayerPos playerPos = new PlayerPos(player);
                                if (player.level().dimension() == Level.NETHER) {

                                    String s = "Overworld Coordinates  " +
                                            "X: " +
                                            Math.round(playerPos.XPos * 8) + " Y: " +
                                            Math.round(playerPos.YPos) + " Z: " +
                                            Math.round(playerPos.ZPos * 8);

                                    player.displayClientMessage(
                                            Component.literal(s),
                                            false
                                    );

                                } else if (player.level().dimension() == Level.OVERWORLD) {

                                    String s = "Nether Coordinates  " +
                                            "X: " +
                                            Math.round(playerPos.XPos / 8) + " Y: " +
                                            Math.round(playerPos.YPos) + " Z: " +
                                            Math.round(playerPos.ZPos / 8);

                                    player.displayClientMessage(
                                            Component.literal(s),
                                            false
                                    );
                                }

                                return 1;
                            })
            );
        });

}}