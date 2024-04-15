/*    */ package dev.schubilegend.Utils;
/*    */ 
/*    */ import java.lang.reflect.Field;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.util.Session;
/*    */ import net.minecraftforge.fml.relauncher.ReflectionHelper;
/*    */ 
/*    */ public class SessionChanger
/*    */ {
/*    */   public static void setSession(Session session) {
/* 11 */     Field sessionField = ReflectionHelper.findField(Minecraft.class, new String[] { "session", "field_71449_j" });
/* 12 */     ReflectionHelper.setPrivateValue(Field.class, sessionField, Integer.valueOf(sessionField.getModifiers() & 0xFFFFFFEF), new String[] { "modifiers" });
/* 13 */     ReflectionHelper.setPrivateValue(Minecraft.class, Minecraft.func_71410_x(), session, new String[] { "session", "field_71449_j" });
/*    */   }
/*    */ }


/* Location:              C:\Users\himme\OneDrive\Skrivebord\Authentication.jar!\dev\schubilegend\Utils\SessionChanger.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */