import { Component, OnInit } from '@angular/core';
import { PartnerService } from '../../services/partner.service';
import { Partner } from '../../models/partner.interface';

@Component({
  selector: 'app-partner-list',
  templateUrl: './partner-list.component.html',
  styleUrls: ['./partner-list.component.scss']
})
export class PartnerListComponent implements OnInit {
  partners: Partner[] = [];

  constructor(private partnerService: PartnerService) { }

  ngOnInit(): void {
    this.partnerService.getPartners().subscribe((data: Partner[]) => {
      this.partners = data;
    });
  }

  deleteUser(id: number): void {
    this.partnerService.deletePartner(id).subscribe(() => {
      this.partners = this.partners.filter(partner => partner.id !== id);
    });
  }
}
