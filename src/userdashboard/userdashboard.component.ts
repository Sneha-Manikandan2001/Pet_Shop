import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { CommonModule } from '@angular/common';
@Component({
  selector: 'app-userdashboard',
  imports: [RouterOutlet,CommonModule],
  templateUrl: './userdashboard.component.html',
  styleUrl: './userdashboard.component.css'
})
export class UserdashboardComponent {
  slides = [
    'assets/userd/bg_1.jpg',
    'assets/userd/bg_2.jpg'
  ];

  Logos = [
    'assets/userd/d1.jpg',
    'assets/userd/d2.jpg',
    'assets/userd/d3.jpg'
  ]
  currentSlide = 0;

  getTransform() {
    return `translateX(-${this.currentSlide * 100}%)`;
  }

  moveSlide(direction: number) {
    const totalSlides = this.slides.length;
    this.currentSlide = (this.currentSlide + direction + totalSlides) % totalSlides;
  }



  
}
