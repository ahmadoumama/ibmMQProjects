export interface Message {
    id: number;
    payload: string;
    receivedAt: Date;
    status?: string;
    type?: string;
    partnerId?: number;
}
