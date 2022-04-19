package com.samsonmarikwa.restservices.config;

import io.micrometer.appoptics.AppOpticsConfig;
import io.micrometer.appoptics.AppOpticsMeterRegistry;
import io.micrometer.core.instrument.Clock;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.lang.Nullable;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MonitoringConfig {
   
   // Generate token from https://my.appoptics.com/organization/tokens
   // In Production systems, this should be passed from property file
   private final String MY_TOKEN = "tv5JRP0Yy4JTa2nIeg2tinxMqCMcDKkizp8nsQ_Idi6y5qwLtWSGAotTBicMemsXff_3Twg";
   
   AppOpticsConfig appopticsConfig = new AppOpticsConfig() {
      
      @Override
      public String apiToken() {
         return MY_TOKEN;
      }
      
      @Override
      @Nullable
      public String get(String k) {
         return null;
      }
   };
   MeterRegistry registry = new AppOpticsMeterRegistry(appopticsConfig, Clock.SYSTEM);
}
