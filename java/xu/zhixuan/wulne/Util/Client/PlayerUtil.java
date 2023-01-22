package xu.zhixuan.wulne.Util.Client;

import java.util.ArrayList;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.*;
import org.lwjgl.util.vector.Vector3f;

public class PlayerUtil {
	private static Minecraft mc = Minecraft.getMinecraft();
	xu.zhixuan.wulne.Util.timer.Timer timer = new xu.zhixuan.wulne.Util.timer.Timer();
	xu.zhixuan.wulne.Util.timer.Timer lastCheck = new xu.zhixuan.wulne.Util.timer.Timer();
	public boolean shouldslow = false;
	public boolean collided = false;

	public static void sendMessage(String msg) {
		if(mc.theWorld != null) {
			String translated = I18n.format(msg);
			if(translated != null){
				msg = translated;
			}

			mc.ingameGUI.getChatGUI().printChatMessage(new ChatComponentText(EnumChatFormatting.YELLOW + "[" + "Wulne" + "]" + EnumChatFormatting.GRAY + msg));
		} else {
			System.out.println(msg);
		}
	}

	public static void sendMessageClean(String msg) {
		if(mc.theWorld != null) {
			String translated = I18n.format(msg);
			if(translated != null){
				msg = translated;
			}

			mc.ingameGUI.getChatGUI().printChatMessage(new ChatComponentText(msg));
		} else {
			System.out.println(msg);
		}
	}

	public static boolean onServer(String server) {
		if(mc.theWorld != null) {
			if (!mc.isSingleplayer() && mc.getCurrentServerData().serverIP.toLowerCase().contains(server.toLowerCase())) {
				return true;
			}
		}
		return false;
	}

	public static boolean isInWater() {
		if (mc.theWorld
				.getBlockState(
						new BlockPos(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ))
				.getBlock().getMaterial() == Material.water) {
			return true;
		}
		return false;
	}

	public static double getSpeed() {
		Minecraft.getMinecraft();
		Minecraft.getMinecraft();
		Minecraft.getMinecraft();
		Minecraft.getMinecraft();
		return Math.sqrt(mc.thePlayer.motionX * mc.thePlayer.motionX
				+ mc.thePlayer.motionZ * mc.thePlayer.motionZ);
	}

	public static Block getBlockUnderPlayer(EntityPlayer inPlayer) {
		return PlayerUtil.getBlock(new BlockPos(inPlayer.posX, inPlayer.posY - 1.0, inPlayer.posZ));
	}

	public static Block getBlock(BlockPos pos) {
		Minecraft.getMinecraft();
		return mc.theWorld.getBlockState(pos).getBlock();
	}

	public static Block getBlockAtPosC(EntityPlayer inPlayer, double x2, double y2, double z2) {
		return PlayerUtil.getBlock(new BlockPos(inPlayer.posX - x2, inPlayer.posY - y2, inPlayer.posZ - z2));
	}

	public static ArrayList<Vector3f> vanillaTeleportPositions(double tpX, double tpY, double tpZ, double speed) {
		ArrayList<Vector3f> positions = new ArrayList<Vector3f>();
		Minecraft mc2 = Minecraft.getMinecraft();
		double posX = tpX - mc.thePlayer.posX;
		double posY = tpY - (mc.thePlayer.posY + (double) mc.thePlayer.getEyeHeight() + 1.1);
		double posZ = tpZ - mc.thePlayer.posZ;
		float yaw = (float) (Math.atan2(posZ, posX) * 180.0 / 3.141592653589793 - 90.0);
		float pitch = (float) ((-Math.atan2(posY, Math.sqrt(posX * posX + posZ * posZ))) * 180.0 / 3.141592653589793);
		double tmpX = mc.thePlayer.posX;
		double tmpY = mc.thePlayer.posY;
		double tmpZ = mc.thePlayer.posZ;
		double steps = 1.0;
		double d2 = speed;
		while (d2 < PlayerUtil.getDistance(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ,
				tpX, tpY, tpZ)) {
			steps += 1.0;
			d2 += speed;
		}
		d2 = speed;
		while (d2 < PlayerUtil.getDistance(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ,
				tpX, tpY, tpZ)) {
			tmpX = mc.thePlayer.posX - Math.sin(PlayerUtil.getDirection(yaw)) * d2;
			tmpZ = mc.thePlayer.posZ + Math.cos(PlayerUtil.getDirection(yaw)) * d2;
			positions.add(new Vector3f((float) tmpX, (float) (tmpY -= (mc.thePlayer.posY - tpY) / steps),
					(float) tmpZ));
			d2 += speed;
		}
		positions.add(new Vector3f((float) tpX, (float) tpY, (float) tpZ));
		return positions;
	}

	public static float getDirection(float yaw) {
		Minecraft.getMinecraft();
		if (mc.thePlayer.moveForward < 0.0f) {
			yaw += 180.0f;
		}
		float forward = 1.0f;
		Minecraft.getMinecraft();
		if (mc.thePlayer.moveForward < 0.0f) {
			forward = -0.5f;
		} else {
			Minecraft.getMinecraft();
			if (mc.thePlayer.moveForward > 0.0f) {
				forward = 0.5f;
			}
		}
		Minecraft.getMinecraft();
		if (mc.thePlayer.moveStrafing > 0.0f) {
			yaw -= 90.0f * forward;
		}
		Minecraft.getMinecraft();
		if (mc.thePlayer.moveStrafing < 0.0f) {
			yaw += 90.0f * forward;
		}
		return yaw *= 0.017453292f;
	}

	public static double getDistance(double x1, double y1, double z1, double x2, double y2, double z2) {
		double d0 = x1 - x2;
		double d1 = y1 - y2;
		double d2 = z1 - z2;
		return MathHelper.sqrt_double(d0 * d0 + d1 * d1 + d2 * d2);
	}
}
