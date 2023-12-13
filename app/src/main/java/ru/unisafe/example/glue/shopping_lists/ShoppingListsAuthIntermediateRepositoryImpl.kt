package ru.unisafe.example.glue.shopping_lists

import ru.unisafe.data.AuthDataRepository
import ru.unisafe.example.navigation.Navigator
import ru.unisafe.example.navigation.Screen
import ru.unisafe.shopping_lists.domain.repositories.ShoppingListsAuthRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ShoppingListsAuthIntermediateRepositoryImpl @Inject constructor(
    private val authDataRepository: AuthDataRepository,
    private val navigator: Navigator
) : ShoppingListsAuthRepository {
    override suspend fun logout() {
        authDataRepository.logout()
        navigator {
            navigate(Screen.Auth.route) {
                popUpTo(Screen.ShoppingLists.route){
                    inclusive = true
                }
            }
        }
    }
}