package io.anuke.mindustry.world.blocks.types.distribution;

import io.anuke.mindustry.resource.Liquid;
import io.anuke.mindustry.world.Tile;
import io.anuke.ucore.graphics.Draw;

public class LiquidRouter extends Conduit{

	public LiquidRouter(String name) {
		super(name);
		rotate = false;
		solid = true;
		flowfactor = 1f;
		liquidCapacity = 30f;
	}
	
	@Override
	public void update(Tile tile){
		LiquidEntity entity = tile.entity();

		float flow = entity.liquidAmount / flowfactor / 4f;

		for(int i = 0; i < 4; i ++) {
			tryMoveLiquid(tile, tile.getNearby()[i], flow);

				//tile.setRotation((byte) ((tile.getRotation() + 1) % 4));
		}
	}
	
	@Override
	public void handleLiquid(Tile tile, Tile source, Liquid liquid, float amount){
		super.handleLiquid(tile, source, liquid, amount);
		tile.setExtra((byte)tile.relativeTo(source.x, source.y));
	}
	
	@Override
	public void draw(Tile tile){
		LiquidEntity entity = tile.entity();
		Draw.rect(name(), tile.worldx(), tile.worldy());
		
		if(entity.liquid == null) return;
		
		Draw.color(entity.liquid.color);
		Draw.alpha(entity.liquidAmount / liquidCapacity);
		Draw.rect("blank", tile.worldx(), tile.worldy(), 2, 2);
		Draw.color();
	}

}
