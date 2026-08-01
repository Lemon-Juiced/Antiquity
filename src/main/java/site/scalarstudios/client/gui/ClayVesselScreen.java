package site.scalarstudios.client.gui;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;
import site.scalarstudios.Antiquity;
import site.scalarstudios.menu.ClayVesselMenu;

public class ClayVesselScreen extends AbstractContainerScreen<ClayVesselMenu> {
    private static final Identifier TEXTURE = Identifier.fromNamespaceAndPath(Antiquity.MODID, "textures/gui/generic_1to1.png");
    private static final int PROGRESS_X = 77;
    private static final int PROGRESS_Y = 34;
    private static final int PROGRESS_WIDTH = 27;
    private static final int PROGRESS_HEIGHT = 16;

    public ClayVesselScreen(ClayVesselMenu menu, Inventory inventory, Component title) {
        super(menu, inventory, title);
    }

    @Override
    protected void init() {
        super.init();
        this.titleLabelX = (this.imageWidth - this.font.width(this.title)) / 2;
    }

    @Override
    public void extractBackground(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a) {
        super.extractBackground(graphics, mouseX, mouseY, a);
        int xo = this.leftPos;
        int yo = this.topPos;
        graphics.blit(RenderPipelines.GUI_TEXTURED, TEXTURE, xo, yo, 0.0F, 0.0F, this.imageWidth, this.imageHeight, 256, 256);

        int progressWidth = Mth.ceil(this.menu.getProgress() * PROGRESS_WIDTH);
        if (progressWidth > 0) {
            graphics.fill(xo + PROGRESS_X, yo + PROGRESS_Y, xo + PROGRESS_X + progressWidth, yo + PROGRESS_Y + PROGRESS_HEIGHT, 0x80FFFFFF);
        }
    }
}
