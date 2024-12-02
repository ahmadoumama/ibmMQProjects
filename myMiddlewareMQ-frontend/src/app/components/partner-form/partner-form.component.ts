import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { PartnerService } from '../../services/partner.service';
@Component({
  selector: 'app-partner-form',
  templateUrl: './partner-form.component.html',
  styleUrl: './partner-form.component.scss'
})
export class PartnerFormComponent {
  partnerForm: FormGroup;

  constructor(private fb: FormBuilder, private partnerService: PartnerService) {
    this.partnerForm= this.fb.group({
      alias: ['', Validators.required],
      type: ['', Validators.required],
      direction: ['', Validators.required],
      application: [''],
      processedFlowType: ['', Validators.required],
      description: ['', Validators.required]
    });
  }

  addUser(): void {
    if (this.partnerForm.valid) {
      this.partnerService.addUser(this.partnerForm.value).subscribe(user => {
        // handle success
      });
    }
  }
}

