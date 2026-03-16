import { Component, OnInit } from '@angular/core';
import { Product } from '../../models/product';
import { CartService } from '../cart.service';
import { MatCard } from '@angular/material/card';
import { MatList, MatListItem, MatListItemLine, MatListItemTitle } from '@angular/material/list';
import { CommonModule, CurrencyPipe } from '@angular/common';
import { MatButton } from '@angular/material/button';

@Component({
  selector: 'app-cart-view',
  imports: [
    CommonModule,
    MatCard,
    MatList,
    MatListItem,
    MatListItemTitle,
    CurrencyPipe,
    MatListItemLine,
    MatButton,
  ],
  templateUrl: './cart-view.component.html',
  styleUrl: './cart-view.component.css',
})
export class CartViewComponent implements OnInit {
  cartItems: Product[] = [];
  totalPrice: number = 0;
  isLoading: boolean = true;

  constructor(private cartService: CartService) {}

  ngOnInit(): void {
    this.cartService.getCartItems().subscribe((data) => {
      this.cartItems = data;
      this.totalPrice = this.getTotalPrice();
      this.isLoading = false;
    });
  }

  getTotalPrice(): number {
    return this.cartItems.reduce((acc, item) => acc + item.price, 0);
  }

  clearCart(): void {
    this.cartService.clearCart().subscribe();
  }
}
