package com.github.sergey_kornyushin;

import dagger.hilt.InstallIn;
import dagger.hilt.codegen.OriginatingElement;
import dagger.hilt.components.SingletonComponent;
import dagger.hilt.internal.GeneratedEntryPoint;

@OriginatingElement(
    topLevelClass = FilmsApplication.class
)
@GeneratedEntryPoint
@InstallIn(SingletonComponent.class)
public interface FilmsApplication_GeneratedInjector {
  void injectFilmsApplication(FilmsApplication filmsApplication);
}
