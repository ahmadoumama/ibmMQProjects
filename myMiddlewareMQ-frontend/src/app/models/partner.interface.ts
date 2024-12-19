export interface Partner {
    id: number;
    alias: string;
    type: string;
    direction: string;
    processedFlowType: string;
    description?: string;
    active?: boolean;
    createdAt?: Date;
    updatedAt?: Date;
}
