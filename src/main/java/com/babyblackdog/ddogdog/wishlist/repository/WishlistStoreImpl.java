package com.babyblackdog.ddogdog.wishlist.repository;

import com.babyblackdog.ddogdog.wishlist.model.Wishlist;
import com.babyblackdog.ddogdog.wishlist.service.WishlistStore;
import org.springframework.stereotype.Component;

@Component
public class WishlistStoreImpl implements WishlistStore {

    private final WishlistRepository repository;

    public WishlistStoreImpl(WishlistRepository repository) {
        this.repository = repository;
    }

    @Override
    public Wishlist registerWishlist(Wishlist wishlist) {
        return repository.save(wishlist);
    }

    @Override
    public void deleteWishlist(Wishlist wishlist) {
        repository.delete(wishlist);
    }
}
