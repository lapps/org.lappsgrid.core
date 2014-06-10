package org.lappsgrid.core;

import org.lappsgrid.api.Data;
import org.lappsgrid.discriminator.Discriminator;
import org.lappsgrid.discriminator.DiscriminatorRegistry;

/**
 * @author Keith Suderman
 */
public final class Helpers
{
   public static long type(Data data)
   {
      Discriminator d = DiscriminatorRegistry.getByUri(data.getDiscriminator());
      if (d == null)
      {
         return -1;
      }
      return d.getId();
   }

   public static String uri(Data data)
   {
      Discriminator d = DiscriminatorRegistry.getByUri(data.getDiscriminator());
      if (d == null)
      {
         return null;
      }
      return d.getUri();
   }

   public static String name(Data data)
   {
      Discriminator d = DiscriminatorRegistry.getByUri(data.getDiscriminator());
      if (d == null)
      {
         return null;
      }
      return d.getName();
   }

   private Helpers()
   {

   }
}
