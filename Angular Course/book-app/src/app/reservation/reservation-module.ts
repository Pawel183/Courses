import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReservationList } from '../reservation-list/reservation-list';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

@NgModule({
  declarations: [],
  imports: [CommonModule, ReservationList, ReservationList, FormsModule, ReactiveFormsModule],
})
export class ReservationModule {}
