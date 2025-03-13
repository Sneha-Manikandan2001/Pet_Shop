import { ApplicationConfig, NgModule, provideZoneChangeDetection,importProvidersFrom } from '@angular/core';
import { provideRouter } from '@angular/router';
 
import { JwtInterceptor, JwtModule } from "@auth0/angular-jwt";
import { HTTP_INTERCEPTORS, HttpClientModule } from "@angular/common/http";
import { routes } from './app.routes';
import{
  provideHttpClient,
  withInterceptorsFromDi,
  }  from '@angular/common/http';
import { AppComponent } from './app.component';
 
export function tokenGetter() {
  return localStorage.getItem("token");
}
export const appConfig: ApplicationConfig = {
  providers: [
   
    provideHttpClient(withInterceptorsFromDi()),
    provideZoneChangeDetection({ eventCoalescing: true }),
    importProvidersFrom( HttpClientModule,
      JwtModule.forRoot({
          config: {
              tokenGetter: ()=>{
                console.log("token shared")
                return localStorage.getItem("token")},
              allowedDomains: ["localhost:8080"]
             
          },
      }),
  ),
    provideRouter(routes),
    { provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true }
 
  ]
};
export class AppModule {}