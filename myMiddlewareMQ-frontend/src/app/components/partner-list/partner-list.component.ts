import { Component, OnInit } from '@angular/core';
import { PartnerService } from '../../services/partner.service';



@Component({
  selector: 'app-partner-list',
  templateUrl: './partner-list.component.html',
  styleUrl: './partner-list.component.scss'
})
export class PartnerListComponent implements OnInit {
  partners: any[] = [];

  constructor(private partnerService: PartnerService) { }

  ngOnInit(): void {
    this.partnerService.getUsers().subscribe(data => {
      this.partners = data;
    });
  }

  deleteUser(id: number): void {
    this.partnerService.deleteUser(id).subscribe(() => {
      this.partners = this.partners.filter(partner => partner.id !== id);
    });
  }
}

