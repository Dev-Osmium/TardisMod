package tardis.client.renderer;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import tardis.TardisMod;
import tardis.client.renderer.model.TardisBlockModel;
import tardis.client.renderer.model.TardisStickModel;
import tardis.common.blocks.TardisAbstractBlock;
import tardis.common.tileents.TardisComponentTileEntity;
import tardis.common.tileents.components.TardisTEComponent;

public class TardisComponentRenderer extends TardisAbstractBlockRenderer
{
	TardisBlockModel block = new TardisBlockModel();
	TardisStickModel stick = new TardisStickModel();
	
	@Override
	public TardisAbstractBlock getBlock()
	{
		return TardisMod.componentBlock;
	}
	
	private void renderStick(ResourceLocation tn, double x, double y, double r, double p, double yaw)
	{
		GL11.glPushMatrix();
		GL11.glTranslated(0.5, 0.5,0.5);
		GL11.glRotated(r, 0, 0, 1);
		GL11.glRotated(p, 0, 1, 0);
		GL11.glRotated(yaw, 1, 0, 0);
		GL11.glTranslated(0.46875, x,y-0.03125);
		bindTexture(tn);
		stick.render(null, 0, 0, 0,0,0, 0.06125F);
		GL11.glPopMatrix();
	}
	
	private void renderStick(String tn,double x, double y, double yaw)
	{
		ResourceLocation res = new ResourceLocation("tardismod","textures/models/"+tn+".png");
		renderStick(res,x,y,0,0,yaw);
		renderStick(res,x,y,-90,0,yaw);
		renderStick(res,x,y,90,0,yaw);
		renderStick(res,x,y,0,180,yaw);
		renderStick(res,x,y,0,90,yaw);
		renderStick(res,x,y,0,-90,yaw);
	}

	@Override
	public void renderBlock(Tessellator tess, TileEntity te, int x, int y, int z)
	{
		/*
		int metadata = te.worldObj.getBlockMetadata(x, y, z);
		if(metadata == 0)
			bindTexture(new ResourceLocation("tardismod","textures/models/TardisOpenRoundel.png"));
		else
			bindTexture(new ResourceLocation("tardismod","textures/models/TardisOpenCorridorRoundel.png"));
		block.render(null, 0, 0, 0, 0, 0, 0.06125F);*/
		if(te instanceof TardisComponentTileEntity)
		{
			TardisComponentTileEntity tcte = ((TardisComponentTileEntity)te);
			double ang = 360 / TardisTEComponent.values().length;
			if(tcte.hasComponent(TardisTEComponent.GRID))
				renderStick("stickGrid",0.1,0, 0*ang);
			if(tcte.hasComponent(TardisTEComponent.TRANSMAT))
				renderStick("stickTrans",0.1,0,1*ang);
			if(tcte.hasComponent(TardisTEComponent.ENERGY))
				renderStick("stickEnergy",0.1,0,2*ang);
			if(tcte.hasComponent(TardisTEComponent.INVENTORY))
				renderStick("stickInv",0.1,0,3*ang);
			if(tcte.hasComponent(TardisTEComponent.FLUID))
				renderStick("stickFlu",0.1,0,4*ang);
		}
	}

}