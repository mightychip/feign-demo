# Woah!!  A README?!
This will eventually get filled in with greater detail.  For now, this is a bit of a hacky placeholder to provide a rough idea of how this works.

The code you see here will be featured in an upcoming blog post about OpenFeign/Spring Boot and is also occasionally used as teaching material.  What exists now is probably pretty incomplete and spotty.

# OpenFeign Spring Boot Integration Demo
This is a simple demo showing the power of OpenFeign in a Spring Boot application.  

Instead of using the vanilla Feign annotations, we're using almost exclusively Spring annotations in order to create Service interfaces which can be shared between the service and the client.  These are accompanied by any POJOs necessary to represent the ReST assets transferred by the service.  

The idea here is that you create a module which is easily shared between projects allowing both the client and service to stay easily in sync in terms of a service's resources and assets.

## Resilience4J
I've plugged in Resilience4J for fault tolerance.  This is kind of a hacky implementation, but my end goal was actually to use some annotation driven integrations.  Sadly, at the time of writing this, those didn't work... so they were not included.  More reading is probably required on that one.  The examle is included, but is non-functional.

## Services
This demo is build from three ReST services, two of which (`service-alpha` and `service-beta`) are called by the third (`service-omega`).  The shared interface and ReST asset are found in `service-alpha-model` and `service-beta-model`.  In retrospect, these weren't exactly the clearest choices for module names.  Oops.

`Book` assets are served up by the `service-alpha` micro-service and `Review` assets are served up by the `service-beta` micro-service. 

## Database
Both `service-alpha` and `service-beta` maintain H2 in-memory databases. These are populated at runtime by `BookLoaderService` and `ReviewLoaderService` respectively.  This is just for ease of use.  These classes can be disabled by ensuring that the `sample-data` profile is _not_ active. 