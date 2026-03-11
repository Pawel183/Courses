import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { ProductModule } from './product/product-module';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, ProductModule],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('amazing-animals-paintings');
}
