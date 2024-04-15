/*     */ package dev.schubilegend.GUI;
/*     */ 
/*     */ import dev.schubilegend.SchubiAuth;
/*     */ import dev.schubilegend.Utils.APIUtils;
/*     */ import dev.schubilegend.Utils.SessionChanger;
/*     */ import java.awt.Color;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Objects;
/*     */ import net.minecraft.client.gui.GuiButton;
/*     */ import net.minecraft.client.gui.GuiScreen;
/*     */ import net.minecraft.client.gui.GuiTextField;
/*     */ import net.minecraft.client.gui.ScaledResolution;
/*     */ import net.minecraft.util.Session;
/*     */ import org.lwjgl.input.Keyboard;
/*     */ 
/*     */ 
/*     */ public class ChangerGUI
/*     */   extends GuiScreen
/*     */ {
/*     */   private final GuiScreen previousScreen;
/*  22 */   private String status = "";
/*     */   private GuiTextField nameField;
/*     */   private GuiTextField skinField;
/*     */   private ScaledResolution sr;
/*  26 */   private ArrayList<GuiTextField> textFields = new ArrayList<>();
/*     */   
/*     */   public ChangerGUI(GuiScreen previousScreen) {
/*  29 */     this.previousScreen = previousScreen;
/*     */   }
/*     */   
/*     */   public void func_73866_w_() {
/*  33 */     Keyboard.enableRepeatEvents(true);
/*  34 */     this.sr = new ScaledResolution(this.field_146297_k);
/*  35 */     this.nameField = new GuiTextField(1, this.field_146297_k.field_71466_p, this.sr.func_78326_a() / 2 - 100, this.sr.func_78328_b() / 2, 97, 20);
/*  36 */     this.nameField.func_146203_f(16);
/*  37 */     this.nameField.func_146195_b(true);
/*  38 */     this.skinField = new GuiTextField(2, this.field_146297_k.field_71466_p, this.sr.func_78326_a() / 2 + 3, this.sr.func_78328_b() / 2, 97, 20);
/*  39 */     this.skinField.func_146203_f(32767);
/*  40 */     this.textFields.add(this.nameField);
/*  41 */     this.textFields.add(this.skinField);
/*  42 */     this.field_146292_n.add(new GuiButton(3100, this.sr.func_78326_a() / 2 - 100, this.sr.func_78328_b() / 2 + 25, 97, 20, "Change Name"));
/*  43 */     this.field_146292_n.add(new GuiButton(3200, this.sr.func_78326_a() / 2 + 3, this.sr.func_78328_b() / 2 + 25, 97, 20, "Change Skin"));
/*  44 */     this.field_146292_n.add(new GuiButton(3300, this.sr.func_78326_a() / 2 - 100, this.sr.func_78328_b() / 2 + 50, 200, 20, "Back"));
/*  45 */     super.func_73866_w_();
/*     */   }
/*     */   
/*     */   public void func_146281_b() {
/*  49 */     Keyboard.enableRepeatEvents(false);
/*  50 */     super.func_146281_b();
/*     */   }
/*     */   
/*     */   public void func_73863_a(int mouseX, int mouseY, float partialTicks) {
/*  54 */     func_146276_q_();
/*  55 */     this.field_146297_k.field_71466_p.func_78276_b(this.status, this.sr.func_78326_a() / 2 - this.field_146297_k.field_71466_p.func_78256_a(this.status) / 2, this.sr.func_78328_b() / 2 - 40, Color.WHITE.getRGB());
/*  56 */     this.field_146297_k.field_71466_p.func_78276_b("Name:", this.sr.func_78326_a() / 2 - 99, this.sr.func_78328_b() / 2 - 15, Color.WHITE.getRGB());
/*  57 */     this.field_146297_k.field_71466_p.func_78276_b("Skin:", this.sr.func_78326_a() / 2 + 4, this.sr.func_78328_b() / 2 - 15, Color.WHITE.getRGB());
/*  58 */     this.nameField.func_146194_f();
/*  59 */     this.skinField.func_146194_f();
/*  60 */     super.func_73863_a(mouseX, mouseY, partialTicks);
/*     */   }
/*     */   
/*     */   protected void func_146284_a(GuiButton button) throws IOException {
/*  64 */     if (button.field_146127_k == 3100) {
/*  65 */       String newName = this.nameField.func_146179_b();
/*  66 */       if (Objects.equals(SchubiAuth.originalSession.func_148254_d(), this.field_146297_k.func_110432_I().func_148254_d())) {
/*  67 */         this.status = "§4Prevented you from changing the name of your main account!";
/*     */       } else {
/*  69 */         (new Thread(() -> {
/*     */               try {
/*     */                 int statusCode = APIUtils.changeName(newName, this.field_146297_k.func_110432_I().func_148254_d());
/*     */                 
/*     */                 if (statusCode == 200) {
/*     */                   this.status = "§2Successfully changed name!";
/*     */                   SessionChanger.setSession(new Session(newName, this.field_146297_k.func_110432_I().func_148255_b(), this.field_146297_k.func_110432_I().func_148254_d(), "mojang"));
/*     */                 } else if (statusCode == 429) {
/*     */                   this.status = "§4Error: Too many requests!";
/*     */                 } else if (statusCode == 400) {
/*     */                   this.status = "§4Error: Invalid name!";
/*     */                 } else if (statusCode == 401) {
/*     */                   this.status = "§4Error: Invalid token!";
/*     */                 } else if (statusCode == 403) {
/*     */                   this.status = "§4Error: Name is unavailable/Player already changed name in the last 35 days";
/*     */                 } else {
/*     */                   this.status = "§4An unknown error occurred!";
/*     */                 } 
/*  87 */               } catch (Exception e) {
/*     */                 this.status = "§4An unknown error occurred!";
/*     */                 e.printStackTrace();
/*     */               } 
/*  91 */             })).start();
/*     */       } 
/*     */     } 
/*  94 */     if (button.field_146127_k == 3200) {
/*  95 */       String newSkin = this.skinField.func_146179_b();
/*  96 */       (new Thread(() -> {
/*     */             try {
/*     */               int statusCode = APIUtils.changeSkin(newSkin, this.field_146297_k.func_110432_I().func_148254_d());
/*     */               
/*     */               if (statusCode == 200) {
/*     */                 this.status = "§2Successfully changed skin!";
/*     */               } else if (statusCode == 429) {
/*     */                 this.status = "§4Error: Too many requests!";
/*     */               } else if (statusCode == 401) {
/*     */                 this.status = "§4Error: Invalid token!";
/*     */               } else {
/*     */                 this.status = "§4Error: Invalid Skin";
/*     */               } 
/* 109 */             } catch (Exception e) {
/*     */               this.status = "§4An unknown error occurred!";
/*     */               e.printStackTrace();
/*     */             } 
/* 113 */           })).start();
/*     */     } 
/* 115 */     if (button.field_146127_k == 3300) {
/* 116 */       this.field_146297_k.func_147108_a(this.previousScreen);
/*     */     }
/* 118 */     super.func_146284_a(button);
/*     */   }
/*     */   
/*     */   protected void func_73869_a(char typedChar, int keyCode) throws IOException {
/* 122 */     this.nameField.func_146201_a(typedChar, keyCode);
/* 123 */     this.skinField.func_146201_a(typedChar, keyCode);
/*     */     
/* 125 */     if (1 == keyCode) { this.field_146297_k.func_147108_a(this.previousScreen); }
/* 126 */     else { super.func_73869_a(typedChar, keyCode); }
/*     */   
/*     */   }
/*     */   protected void func_73864_a(int mouseX, int mouseY, int mouseButton) throws IOException {
/* 130 */     super.func_73864_a(mouseX, mouseY, mouseButton);
/* 131 */     boolean prevFocused = false;
/* 132 */     boolean postFocused = false;
/* 133 */     for (GuiTextField text : this.textFields) {
/* 134 */       prevFocused = (text.func_146206_l() || prevFocused);
/* 135 */       text.func_146192_a(mouseX, mouseY, mouseButton);
/* 136 */       postFocused = (text.func_146206_l() || postFocused);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\himme\OneDrive\Skrivebord\Authentication.jar!\dev\schubilegend\GUI\ChangerGUI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */