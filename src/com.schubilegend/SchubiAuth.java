/*    */ package dev.schubilegend;
/*    */ 
/*    */ import dev.schubilegend.GUI.ChangerGUI;
/*    */ import dev.schubilegend.GUI.SessionGUI;
/*    */ import dev.schubilegend.Utils.APIUtils;
/*    */ import java.awt.Color;
/*    */ import java.io.IOException;
/*    */ import java.text.ParseException;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.gui.GuiButton;
/*    */ import net.minecraft.client.gui.GuiScreen;
/*    */ import net.minecraft.util.Session;
/*    */ import net.minecraftforge.client.event.GuiScreenEvent;
/*    */ import net.minecraftforge.common.MinecraftForge;
/*    */ import net.minecraftforge.fml.common.Mod;
/*    */ import net.minecraftforge.fml.common.Mod.EventHandler;
/*    */ import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
/*    */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*    */ import org.lwjgl.opengl.Display;
/*    */ 
/*    */ @Mod(modid = "SchubiAuth", version = "2.0")
/*    */ public class SchubiAuth
/*    */ {
/*    */   public static final String MODID = "SchubiAuth";
/*    */   public static final String VERSION = "2.0";
/* 26 */   public static Minecraft mc = Minecraft.func_71410_x();
/* 27 */   public static Session originalSession = mc.func_110432_I();
/* 28 */   public static String onlineStatus = "§4╳ Offline";
/* 29 */   public static String isSessionValid = "§2✔ Valid";
/*    */   
/*    */   @EventHandler
/*    */   public void preInit(FMLPreInitializationEvent event) {
/* 33 */     MinecraftForge.EVENT_BUS.register(this);
/* 34 */     Display.setTitle("SchubiAuth 2.0");
/*    */   }
/*    */   
/*    */   @SubscribeEvent
/*    */   public void onInitGuiPost(GuiScreenEvent.InitGuiEvent.Post e) throws IOException, ParseException {
/* 39 */     if (e.gui instanceof net.minecraft.client.gui.GuiMultiplayer) {
/* 40 */       e.buttonList.add(new GuiButton(2100, e.gui.field_146294_l - 90, 5, 80, 20, "Login"));
/* 41 */       e.buttonList.add(new GuiButton(2200, e.gui.field_146294_l - 180, 5, 80, 20, "Changer"));
/* 42 */       (new Thread(() -> {
/*    */             try {
/*    */               isSessionValid = APIUtils.validateSession(mc.func_110432_I().func_148254_d()).booleanValue() ? "§2✔ Valid" : "§4╳ Invalid";
/*    */               onlineStatus = APIUtils.checkOnline(mc.func_110432_I().func_111285_a()).booleanValue() ? "§2✔ Online" : "§4╳ Offline";
/* 46 */             } catch (Exception ex) {
/*    */               ex.printStackTrace();
/*    */             } 
/* 49 */           })).start();
/*    */     } 
/*    */   }
/*    */   @SubscribeEvent
/*    */   public void onDrawScreenPost(GuiScreenEvent.DrawScreenEvent.Post e) throws IOException, ParseException {
/* 54 */     if (e.gui instanceof net.minecraft.client.gui.GuiMultiplayer)
/* 55 */       (Minecraft.func_71410_x()).field_71466_p.func_78276_b("§fUser: " + mc.func_110432_I().func_111285_a() + "  §f|  " + onlineStatus + "  §f|  " + isSessionValid, 5, 10, Color.RED.getRGB()); 
/*    */   }
/*    */   
/*    */   @SubscribeEvent
/*    */   public void onActionPerformedPre(GuiScreenEvent.ActionPerformedEvent.Pre e) {
/* 60 */     if (e.gui instanceof net.minecraft.client.gui.GuiMultiplayer) {
/* 61 */       if (e.button.field_146127_k == 2100) {
/* 62 */         Minecraft.func_71410_x().func_147108_a((GuiScreen)new SessionGUI(e.gui));
/*    */       }
/* 64 */       if (e.button.field_146127_k == 2200)
/* 65 */         Minecraft.func_71410_x().func_147108_a((GuiScreen)new ChangerGUI(e.gui)); 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\himme\OneDrive\Skrivebord\Authentication.jar!\dev\schubilegend\SchubiAuth.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */