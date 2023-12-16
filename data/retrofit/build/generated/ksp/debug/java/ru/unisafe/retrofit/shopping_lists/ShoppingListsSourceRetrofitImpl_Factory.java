package ru.unisafe.retrofit.shopping_lists;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava"
})
public final class ShoppingListsSourceRetrofitImpl_Factory implements Factory<ShoppingListsSourceRetrofitImpl> {
  private final Provider<ShoppingListsAPI> shoppingListsAPIProvider;

  public ShoppingListsSourceRetrofitImpl_Factory(
      Provider<ShoppingListsAPI> shoppingListsAPIProvider) {
    this.shoppingListsAPIProvider = shoppingListsAPIProvider;
  }

  @Override
  public ShoppingListsSourceRetrofitImpl get() {
    return newInstance(shoppingListsAPIProvider.get());
  }

  public static ShoppingListsSourceRetrofitImpl_Factory create(
      Provider<ShoppingListsAPI> shoppingListsAPIProvider) {
    return new ShoppingListsSourceRetrofitImpl_Factory(shoppingListsAPIProvider);
  }

  public static ShoppingListsSourceRetrofitImpl newInstance(ShoppingListsAPI shoppingListsAPI) {
    return new ShoppingListsSourceRetrofitImpl(shoppingListsAPI);
  }
}
