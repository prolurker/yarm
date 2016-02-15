package com.prolurker.cm.materials;

import org.lwjgl.opengl.GL11;

import com.prolurker.cm.CM;

import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;

/**
 * Sorry i stole this code from AOBD
 */
public class CMMaterialRenderer implements IItemRenderer {
	public static final CMMaterialRenderer INSTANCE = new CMMaterialRenderer();

	protected static RenderItem itemRender = new RenderItem();

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		return false;
	}
	
	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
		return false;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack stack, Object... data) {
		GL11.glPushMatrix();
		double offset = -0.5;
		if (type == ItemRenderType.EQUIPPED || type == ItemRenderType.EQUIPPED_FIRST_PERSON)
			offset = 0;
		else if (type == ItemRenderType.ENTITY)
			GL11.glScalef(0.5F, 0.5F, 0.5F);

		renderItemAsBlock((RenderBlocks) data[0], stack, offset, offset, offset, 0);
		renderItemAsBlock((RenderBlocks) data[0], stack, offset, offset, offset, 1);
		GL11.glPopMatrix();
	}

	public static void renderItemAsBlock(RenderBlocks renderer, ItemStack stack, double x, double y, double z, int pass) {
		GL11.glPushMatrix();
		Tessellator tessellator = Tessellator.instance;

		int colour = stack.getItem().getColorFromItemStack(stack, pass);
		IIcon icon = stack.getItem().getIcon(stack, pass);

		renderer.setRenderBounds(0, 0, 0, 1, 1, 1);
		GL11.glTranslated(x, y, z);

		float R = (colour >> 16 & 255) / 255.0F;
		float G = (colour >> 8 & 255) / 255.0F;
		float B = (colour & 255) / 255.0F;
		
		CM.logger.info("ANNOYING RENDERER");
		CM.logger.info(pass);
		CM.logger.info(R);
		CM.logger.info(G);
		CM.logger.info(B);
		
		GL11.glColor3f(R, G, B);
		boolean alpha = GL11.glIsEnabled(GL11.GL_ALPHA_TEST);
		if (!alpha)
			GL11.glEnable(GL11.GL_ALPHA_TEST);

		tessellator.startDrawingQuads();
		tessellator.setNormal(0, -1, 0);
		renderer.renderFaceYNeg(null, 0, 0, 0, icon);

		tessellator.setNormal(0, 1, 0);
		renderer.renderFaceYPos(null, 0, 0, 0, icon);

		tessellator.setNormal(0, 0, -1);
		renderer.renderFaceZNeg(null, 0, 0, 0, icon);

		tessellator.setNormal(0, 0, 1);
		renderer.renderFaceZPos(null, 0, 0, 0, icon);

		tessellator.setNormal(-1, 0, 0);
		renderer.renderFaceXNeg(null, 0, 0, 0, icon);

		tessellator.setNormal(1, 0, 0);
		renderer.renderFaceXPos(null, 0, 0, 0, icon);
		tessellator.draw();

		if (!alpha)
			GL11.glDisable(GL11.GL_ALPHA_TEST);
		GL11.glPopMatrix();
	}
}
