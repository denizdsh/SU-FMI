import { Component, input, signal } from '@angular/core';

@Component({
  selector: 'app-counter',
  imports: [],
  templateUrl: './counter.html',
  styleUrl: './counter.scss',
})
export class Counter {
  title = input.required<string>();
  protected readonly count = signal(0);

  increment() {
    this.count.update((c) => c + 1);
  }
  decrement() {
    this.count.update((c) => c - 1);
  }
}
