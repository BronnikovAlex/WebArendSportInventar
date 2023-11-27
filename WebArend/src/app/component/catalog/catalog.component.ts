import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from '../../service/auntification.service';
import { Router } from '@angular/router';
import { Equipment } from '../../model/equipment';
import { EquipmetService } from '../../service/equipmet.service';
import { Carousel, Dropdown, initTE } from 'tw-elements';
import { User } from '../../model/user';

@Component({
  selector: 'app-catalog',
  templateUrl: './catalog.component.html',
  styleUrls: ['./catalog.component.css'],
})
export class CatalogComponent implements OnInit {
  currentUser: any;
  equipmentList: Equipment[] = [];
  filteredEquipmentList: Equipment[] = [];
  selectedCategory: string | null = null;
  selectedMaxPrice: number | null = null;

  imageUrl =
    'https://sbbusiness.ru/upload/resize_cache/iblock/053/540_345_2/053fdfe307db31de5af938469e824a4e.png';

  selectedEquipment: Equipment | null = null;

  constructor(
    private authService: AuthenticationService,
    private router: Router,
    public equipmentService: EquipmetService
  ) {}

  ngOnInit() {
    initTE({ Carousel, Dropdown });

    this.authService.currentUser.subscribe(async (user) => {
      this.currentUser = user;
      await this.loadEquipment();
      console.log('Текущий пользователь (в компоненте):', this.currentUser);
    });

    if (this.currentUser) {
      const storedShoppingCart = this.equipmentService.shoppingCartMap.get(this.currentUser?.login || '') || [];
      this.equipmentService.shoppingCartMap.set(this.currentUser?.login || '', storedShoppingCart);
    }
  }

  logout() {
    this.authService.logout();
    this.router.navigate(['/login']);
    if (this.currentUser) {
      this.equipmentService.clearUserData(this.currentUser.login);
    }
  }

  showDescription(item: any) {
    this.selectedEquipment = item;
  }

  rentEquipment(item: Equipment) {
    if (this.currentUser) {
      console.log('Renting equipment. Item details:', item);
      this.equipmentService.addToShoppingCart(this.currentUser?.login || '', item);
      this.selectedEquipment = null;
    }
  }

  loadEquipment() {
    this.equipmentService.getAllEquipment().subscribe(
      (data) => {
        this.equipmentList = data.map((equipment: Equipment) => {
          return {
            ...equipment,
            imageUrl: `https://sbbusiness.ru/upload/resize_cache/iblock/053/540_345_2/053fdfe307db31de5af938469e824a4e.png`,
          };
        });

        // Инициализируем filteredEquipmentList полным списком
        this.filteredEquipmentList = this.equipmentList;

        if (this.currentUser) {
          const selectedEquipment =
            this.equipmentService.selectedEquipmentMap.get(this.currentUser?.login || '');

          if (selectedEquipment) {
            this.equipmentService.shoppingCartMap.set(
              this.currentUser?.login || '',
              [
                ...(this.equipmentService.shoppingCartMap.get(
                  this.currentUser?.login || ''
                ) || []),
                selectedEquipment,
              ]
            );
            this.equipmentService.saveUserData(this.currentUser?.login || '');
          }
        }
      },
      (error) => {
        console.error('Error fetching equipment:', error);
      }
    );
  }

  closeModal() {
    this.selectedEquipment = null;
  }

  filterEquipment(): void {
    this.filteredEquipmentList = this.equipmentList.filter((equipment) => {
      const categoryFilter = !this.selectedCategory || equipment.nameEquip === this.selectedCategory;
      const priceFilter = !this.selectedMaxPrice || equipment.costArend <= this.selectedMaxPrice;

      return categoryFilter && priceFilter;
    });
  }

  updateFilter(): void {
    this.filterEquipment();
  }
}
