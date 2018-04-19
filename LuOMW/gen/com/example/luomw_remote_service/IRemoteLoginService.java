/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: E:\\androidworkspace\\LuOMW\\src\\com\\example\\luomw_remote_service\\IRemoteLoginService.aidl
 */
package com.example.luomw_remote_service;
public interface IRemoteLoginService extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements com.example.luomw_remote_service.IRemoteLoginService
{
private static final java.lang.String DESCRIPTOR = "com.example.luomw_remote_service.IRemoteLoginService";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an com.example.luomw_remote_service.IRemoteLoginService interface,
 * generating a proxy if needed.
 */
public static com.example.luomw_remote_service.IRemoteLoginService asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof com.example.luomw_remote_service.IRemoteLoginService))) {
return ((com.example.luomw_remote_service.IRemoteLoginService)iin);
}
return new com.example.luomw_remote_service.IRemoteLoginService.Stub.Proxy(obj);
}
@Override public android.os.IBinder asBinder()
{
return this;
}
@Override public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException
{
switch (code)
{
case INTERFACE_TRANSACTION:
{
reply.writeString(DESCRIPTOR);
return true;
}
case TRANSACTION_login:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
java.lang.String _arg1;
_arg1 = data.readString();
boolean _result = this.login(_arg0, _arg1);
reply.writeNoException();
reply.writeInt(((_result)?(1):(0)));
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements com.example.luomw_remote_service.IRemoteLoginService
{
private android.os.IBinder mRemote;
Proxy(android.os.IBinder remote)
{
mRemote = remote;
}
@Override public android.os.IBinder asBinder()
{
return mRemote;
}
public java.lang.String getInterfaceDescriptor()
{
return DESCRIPTOR;
}
@Override public boolean login(java.lang.String name, java.lang.String password) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
boolean _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(name);
_data.writeString(password);
mRemote.transact(Stub.TRANSACTION_login, _data, _reply, 0);
_reply.readException();
_result = (0!=_reply.readInt());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
}
static final int TRANSACTION_login = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
}
public boolean login(java.lang.String name, java.lang.String password) throws android.os.RemoteException;
}
