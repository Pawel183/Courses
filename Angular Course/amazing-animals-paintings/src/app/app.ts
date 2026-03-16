import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { ProductModule } from './product/product-module';
import { MatToolbar, MatToolbarRow } from '@angular/material/toolbar';
import { MatButton } from '@angular/material/button';
import { CartModule } from './cart/cart-module';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, ProductModule, CartModule, MatToolbar, MatToolbarRow, MatButton],
  templateUrl: './app.html',
  styleUrl: './app.css',
})
export class App {
  protected readonly title = signal('amazing-animals-paintings');
}
