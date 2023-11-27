export interface Equipment {
  id: number;
  nameEquip: string;
  description: string;
  costArend: number;
  deposit: number;
  typeEquipment: TypeEquipment;
  viewEquipment: ViewEquipment;
  numberEquipment: number;
  imageUrl: string;
  quantity: number;
}

export interface TypeEquipment {
  id: number;
  nameTypeEquipment: string;
}

export interface ViewEquipment {
  id: number;
  nameViewEquipment: string;
  cost: number;
}
