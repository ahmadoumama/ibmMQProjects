import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { PartnerService } from '../../services/partner.service';
import { Partner } from '../../models/partner.interface';
import { Router } from '@angular/router';

@Component({
  selector: 'app-partner-form',
  templateUrl: './partner-form.component.html',
  styleUrls: ['./partner-form.component.scss']
})
export class PartnerFormComponent {
  partnerForm: FormGroup;

  constructor(
    private fb: FormBuilder, 
    private partnerService: PartnerService,
    private router: Router
  ) {
    this.partnerForm = this.fb.group({
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
      this.partnerService.createPartner(this.partnerForm.value).subscribe((partner: Partner) => {
        // Navigate to partners list after successful creation
        this.router.navigate(['/users']);
      });
    }
  }
}
