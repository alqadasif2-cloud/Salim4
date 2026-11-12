// In-memory stub for FCM push notifications

interface DeviceRecord {
  token: string;
  userId: number | string;
  username?: string;
  deviceId?: string;
  platform?: string;
  deviceModel?: string;
  updatedAt: number;
}

const deviceTokens = new Map<string, DeviceRecord>();

export async function registerDeviceToken(params: {
  token: string;
  userId: number | string;
  username?: string;
  deviceId?: string;
  platform?: string;
  deviceModel?: string;
}): Promise<DeviceRecord> {
  const record: DeviceRecord = {
    ...params,
    updatedAt: Date.now(),
  };
  deviceTokens.set(params.token, record);
  return record;
}

export async function unregisterDeviceToken(
  _userId: number | string,
  token?: string,
  _deviceId?: string
): Promise<void> {
  if (token) {
    deviceTokens.delete(token);
  }
}

export async function sendNotificationToUsers(
  _targetUserIds: (number | string)[],
  _payload: {
    title: string;
    body: string;
    chatType?: string;
    data?: Record<string, string>;
  }
): Promise<{ success: boolean; sentCount: number }> {
  return { success: true, sentCount: deviceTokens.size };
}

export async function dispatchAuthorizedChatPush(_params: {
  senderId: number | string;
  senderName: string;
  senderRole?: string;
  chatType: 'private' | 'channel';
  channelId?: string;
  receiverId?: number | string;
  messageId: string;
  snippet: string;
}): Promise<{ success: boolean; deliveredCount: number }> {
  return { success: true, deliveredCount: 1 };
}
