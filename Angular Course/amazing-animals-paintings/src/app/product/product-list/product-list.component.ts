import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { ProductService } from '../product.service';
import { Product } from '../../models/product';
import {
  MatCard,
  MatCardContent,
  MatCardImage,
  MatCardSubtitle,
  MatCardTitle,
} from '@angular/material/card';
import { CurrencyPipe } from '@angular/common';

@Component({
  selector: 'app-product-list',
  imports: [MatCard, MatCardContent, MatCardTitle, MatCardSubtitle, MatCardImage, CurrencyPipe],
  templateUrl: './product-list.component.html',
  styleUrl: './product-list.component.css',
})
export class ProductListComponent implements OnInit {
  products: Product[] = [];

  constructor(
    private productService: ProductService,
    private cdr: ChangeDetectorRef,
  ) {}

  ngOnInit(): void {
    this.productService.getProducts().subscribe({
      next: (data) => {
        this.products = data;
        this.cdr.detectChanges(); // ← force view update
      },
      error: (err) => console.error('Error:', err),
    });
  }
}
