package com.studentAssist.GCM;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Message.Builder;
import com.google.android.gcm.server.MulticastResult;
import com.google.android.gcm.server.Sender;
import com.studentAssist.classes.AccommodationNotification;
import com.studentAssist.classes.Users;
import com.studentAssist.response.RAccommodationAdd;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class PushNotification
{
  public void processData(RAccommodationAdd advertisement, Set<AccommodationNotification> notificationSet)
  {
    Class objClass = advertisement.getClass();
    List<GCMNotificationData> notificationData = new ArrayList();
    
    Method[] methods = objClass.getMethods();
    Method[] arrayOfMethod1;
    int j = (arrayOfMethod1 = methods).length;
    for (int i = 0; i < j; i++)
    {
      Method method = arrayOfMethod1[i];
      String name = method.getName();
      if (name.startsWith("get")) {
        try
        {
          name = name.substring(3, name.length());
          char[] c = name.toCharArray();
          c[0] = Character.toLowerCase(c[0]);
          name = new String(c);
          
          System.out.println("name==" + name + " value==" + method.invoke(advertisement, null));
          
          notificationData.add(new GCMNotificationData(name, (String) method.invoke(advertisement, null)));
        }
        catch (Exception e)
        {
          e.printStackTrace();
        }
      }
    }
    notificationData.add(new GCMNotificationData("notificationType", "accommodationAdd"));
    createBuilder(notificationSet, notificationData);
  }
  
  private void sendMessage(Message.Builder builder, List<String> gcmIds)
    throws IOException
  {
    String API_KEY = "AIzaSyDGnCgvHV1vDQW-WyDFnVZOuZTZ4X_uNLA";
    Sender sender = new Sender(API_KEY);
    
    Message message = builder.build();
    MulticastResult result = sender.send(message, gcmIds, 1);
    
    System.out.println("result = " + result);
    if (result.getResults() == null)
    {
      int error = result.getFailure();
      System.out.println("Broadcast failure: " + error);
    }
  }
  
  private void createBuilder(Set<AccommodationNotification> notificationSet, List<GCMNotificationData> notificationData)
  {
    Message.Builder builder = new Message.Builder();
    
    String collpaseKey = "gcm_message";
    builder.collapseKey(collpaseKey);
    builder.timeToLive(30);
    builder.delayWhileIdle(true);
    for (GCMNotificationData data : notificationData) {
      builder.addData(data.getParameter1(), data.getParameter2());
    }
    List<String> gcmIds = new ArrayList();
    for (AccommodationNotification notification : notificationSet)
    {
      gcmIds.add(notification.getUser().getGcmId());
      
      System.out.println("notification details==" + notification.getUser().getUserId());
    }
    try
    {
      sendMessage(builder, gcmIds);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
}
