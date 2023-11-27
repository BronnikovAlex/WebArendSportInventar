import {Equipment} from "./equipment";
import {User} from "./user";
import {Refund} from "./refund";
import {ConditionOrder} from "./conditionOrder";

export interface OrderArend {
  idOrder?: number;
  statusOrder?: string;
  dateNachaloArend: string;
  dateEndArend: string;
  term: string;
  equipment: Equipment;
  userArSpIn: User;
  client: User;
  employee: null;
  refund: Refund;
  conditionOrder: ConditionOrder;
}
