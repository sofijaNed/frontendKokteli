import { Injectable } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MessageDialogComponent } from 'src/app/message-dialog/message-dialog.component';

@Injectable({
  providedIn: 'root'
})
export class MessageDialogService {

  constructor(public dialog: MatDialog) { }

  openDialog(isSuccess: boolean, message: string): void {
    this.dialog.open(MessageDialogComponent, {
      width: '250px',
      data: { isSuccess: isSuccess, successMessage: isSuccess ? message : '', errorMessage: isSuccess ? '' : message }
    });
  }
}
