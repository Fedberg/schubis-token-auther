/*    */ package dev.schubilegend.GUI;
/*    */ 
/*    */ import dev.schubilegend.SchubiAuth;
/*    */ import dev.schubilegend.Utils.APIUtils;
/*    */ import dev.schubilegend.Utils.SessionChanger;
/*    */ import java.awt.Color;
/*    */ import java.io.IOException;
/*    */ import net.minecraft.client.gui.GuiButton;
/*    */ import net.minecraft.client.gui.GuiScreen;
/*    */ import net.minecraft.client.gui.GuiTextField;
/*    */ import net.minecraft.client.gui.ScaledResolution;
/*    */ import net.minecraft.util.Session;
/*    */ import org.lwjgl.input.Keyboard;
/*    */ 
/*    */ public class SessionGUI
/*    */   extends GuiScreen
/*    */ {
/*    */   private GuiScreen previousScreen;
/* 19 */   private String status = "Session";
/*    */   private GuiTextField sessionField;
/*    */   private ScaledResolution sr;
/*    */   
/*    */   public SessionGUI(GuiScreen previousScreen) {
/* 24 */     this.previousScreen = previousScreen;
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_73866_w_() {
/* 29 */     Keyboard.enableRepeatEvents(true);
/* 30 */     this.sr = new ScaledResolution(this.field_146297_k);
/*    */     
/* 32 */     this.sessionField = new GuiTextField(1, this.field_146297_k.field_71466_p, this.sr.func_78326_a() / 2 - 100, this.sr.func_78328_b() / 2, 200, 20);
/* 33 */     this.sessionField.func_146203_f(32767);
/* 34 */     this.sessionField.func_146195_b(true);
/*    */     
/* 36 */     this.field_146292_n.add(new GuiButton(1400, this.sr.func_78326_a() / 2 - 100, this.sr.func_78328_b() / 2 + 25, 97, 20, "Login"));
/* 37 */     this.field_146292_n.add(new GuiButton(1500, this.sr.func_78326_a() / 2 + 3, this.sr.func_78328_b() / 2 + 25, 97, 20, "Restore"));
/* 38 */     this.field_146292_n.add(new GuiButton(1600, this.sr.func_78326_a() / 2 - 100, this.sr.func_78328_b() / 2 + 50, 200, 20, "Back"));
/*    */     
/* 40 */     super.func_73866_w_();
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_146281_b() {
/* 45 */     Keyboard.enableRepeatEvents(false);
/*    */     
/* 47 */     super.func_146281_b();
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_73863_a(int mouseX, int mouseY, float partialTicks) {
/* 52 */     func_146276_q_();
/*    */     
/* 54 */     this.field_146297_k.field_71466_p.func_78276_b(this.status, this.sr.func_78326_a() / 2 - this.field_146297_k.field_71466_p.func_78256_a(this.status) / 2, this.sr.func_78328_b() / 2 - 30, Color.WHITE.getRGB());
/* 55 */     this.sessionField.func_146194_f();
/*    */     
/* 57 */     super.func_73863_a(mouseX, mouseY, partialTicks);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void func_146284_a(GuiButton button) throws IOException {
/* 63 */     if (button.field_146127_k == 1400) {
/* 64 */       (new Thread(() -> {
/*    */             try {
/*    */               String token = this.sessionField.func_146179_b();
/*    */               String[] playerInfo = APIUtils.getProfileInfo(token);
/*    */               SessionChanger.setSession(new Session(playerInfo[0], playerInfo[1], token, "mojang"));
/*    */               this.status = "§2Logged in as " + playerInfo[0];
/* 70 */             } catch (Exception e) {
/*    */               this.status = "§4Invalid token";
/*    */               e.printStackTrace();
/*    */             } 
/* 74 */           })).start();
/*    */     }
/*    */     
/* 77 */     if (button.field_146127_k == 1500) {
/* 78 */       SessionChanger.setSession(SchubiAuth.originalSession);
/* 79 */       this.status = "§2Restored session";
/*    */     } 
/*    */ 
/*    */     
/* 83 */     if (button.field_146127_k == 1600) {
/* 84 */       this.field_146297_k.func_147108_a(this.previousScreen);
/*    */     }
/* 86 */     super.func_146284_a(button);
/*    */   }
/*    */   
/*    */   protected void func_73869_a(char typedChar, int keyCode) throws IOException {
/* 90 */     this.sessionField.func_146201_a(typedChar, keyCode);
/*    */     
/* 92 */     if (1 == keyCode) { this.field_146297_k.func_147108_a(this.previousScreen); }
/* 93 */     else { super.func_73869_a(typedChar, keyCode); }
/*    */   
/*    */   }
/*    */ }


/* Location:              C:\Users\himme\OneDrive\Skrivebord\Authentication.jar!\dev\schubilegend\GUI\SessionGUI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */