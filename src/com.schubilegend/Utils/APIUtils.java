/*    */ package dev.schubilegend.Utils;
/*    */ import com.google.gson.JsonObject;
/*    */ import com.google.gson.JsonParser;
/*    */ import java.io.IOException;
/*    */ import java.nio.charset.StandardCharsets;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import org.apache.http.HttpEntity;
/*    */ import org.apache.http.client.methods.CloseableHttpResponse;
/*    */ import org.apache.http.client.methods.HttpGet;
/*    */ import org.apache.http.client.methods.HttpPost;
/*    */ import org.apache.http.client.methods.HttpPut;
/*    */ import org.apache.http.client.methods.HttpUriRequest;
/*    */ import org.apache.http.impl.client.CloseableHttpClient;
/*    */ import org.apache.http.impl.client.HttpClients;
/*    */ import org.apache.http.util.EntityUtils;
/*    */ 
/*    */ public class APIUtils {
/*    */   public static String[] getProfileInfo(String token) throws IOException {
/* 19 */     CloseableHttpClient client = HttpClients.createDefault();
/* 20 */     HttpGet request = new HttpGet("https://api.minecraftservices.com/minecraft/profile");
/* 21 */     request.setHeader("Authorization", "Bearer " + token);
/* 22 */     CloseableHttpResponse response = client.execute((HttpUriRequest)request);
/* 23 */     String jsonString = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
/* 24 */     JsonObject jsonObject = (new JsonParser()).parse(jsonString).getAsJsonObject();
/* 25 */     String IGN = jsonObject.get("name").getAsString();
/* 26 */     String UUID = jsonObject.get("id").getAsString();
/* 27 */     return new String[] { IGN, UUID };
/*    */   }
/*    */   public static Boolean validateSession(String token) throws IOException {
/*    */     try {
/* 31 */       String[] profileInfo = getProfileInfo(token);
/* 32 */       String IGN = profileInfo[0];
/* 33 */       String UUID = profileInfo[1];
/* 34 */       return Boolean.valueOf((IGN.equals(Minecraft.func_71410_x().func_110432_I().func_111285_a()) && UUID.equals(Minecraft.func_71410_x().func_110432_I().func_148255_b())));
/* 35 */     } catch (Exception e) {
/* 36 */       return Boolean.valueOf(false);
/*    */     } 
/*    */   }
/*    */   
/*    */   public static Boolean checkOnline(String UUID) {
/*    */     try {
/* 42 */       CloseableHttpClient client = HttpClients.createDefault();
/* 43 */       HttpGet requests = new HttpGet("https://api.slothpixel.me/api/players/" + UUID);
/* 44 */       CloseableHttpResponse response = client.execute((HttpUriRequest)requests);
/* 45 */       String jsonString = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
/* 46 */       JsonObject jsonObject = (new JsonParser()).parse(jsonString).getAsJsonObject();
/* 47 */       return Boolean.valueOf(jsonObject.get("online").getAsBoolean());
/* 48 */     } catch (Exception e) {
/* 49 */       e.printStackTrace();
/* 50 */       return Boolean.valueOf(false);
/*    */     } 
/*    */   }
/*    */   public static int changeName(String newName, String token) throws IOException {
/* 54 */     CloseableHttpClient client = HttpClients.createDefault();
/* 55 */     HttpPut request = new HttpPut("https://api.minecraftservices.com/minecraft/profile/name/" + newName);
/* 56 */     request.setHeader("Authorization", "Bearer " + token);
/* 57 */     CloseableHttpResponse response = client.execute((HttpUriRequest)request);
/* 58 */     return response.getStatusLine().getStatusCode();
/*    */   }
/*    */   public static int changeSkin(String url, String token) throws IOException {
/* 61 */     CloseableHttpClient client = HttpClients.createDefault();
/* 62 */     HttpPost request = new HttpPost("https://api.minecraftservices.com/minecraft/profile/skins");
/* 63 */     request.setHeader("Authorization", "Bearer " + token);
/* 64 */     request.setHeader("Content-Type", "application/json");
/* 65 */     String jsonString = String.format("{ \"variant\": \"classic\", \"url\": \"%s\"}", new Object[] { url });
/* 66 */     request.setEntity((HttpEntity)new StringEntity(jsonString));
/* 67 */     CloseableHttpResponse response = client.execute((HttpUriRequest)request);
/* 68 */     return response.getStatusLine().getStatusCode();
/*    */   }
/*    */ }


/* Location:              C:\Users\himme\OneDrive\Skrivebord\Authentication.jar!\dev\schubilegend\Utils\APIUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */