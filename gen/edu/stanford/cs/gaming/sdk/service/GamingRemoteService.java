/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: /Volumes/Data/aslai/dev/cs294s/GamingSDK/src/edu/stanford/cs/gaming/sdk/service/GamingRemoteService.aidl
 */
package edu.stanford.cs.gaming.sdk.service;
public interface GamingRemoteService extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements edu.stanford.cs.gaming.sdk.service.GamingRemoteService
{
private static final java.lang.String DESCRIPTOR = "edu.stanford.cs.gaming.sdk.service.GamingRemoteService";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an edu.stanford.cs.gaming.sdk.service.GamingRemoteService interface,
 * generating a proxy if needed.
 */
public static edu.stanford.cs.gaming.sdk.service.GamingRemoteService asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = (android.os.IInterface)obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof edu.stanford.cs.gaming.sdk.service.GamingRemoteService))) {
return ((edu.stanford.cs.gaming.sdk.service.GamingRemoteService)iin);
}
return new edu.stanford.cs.gaming.sdk.service.GamingRemoteService.Stub.Proxy(obj);
}
public android.os.IBinder asBinder()
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
case TRANSACTION_getCounter:
{
data.enforceInterface(DESCRIPTOR);
int _result = this.getCounter();
reply.writeNoException();
reply.writeInt(_result);
return true;
}
case TRANSACTION_getLocationString:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _result = this.getLocationString();
reply.writeNoException();
reply.writeString(_result);
return true;
}
case TRANSACTION_addApp:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
java.lang.String _arg1;
_arg1 = data.readString();
boolean _result = this.addApp(_arg0, _arg1);
reply.writeNoException();
reply.writeInt(((_result)?(1):(0)));
return true;
}
case TRANSACTION_doGet:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
this.doGet(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_putGameObject:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
this.putGameObject(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_getNextCompletedTask:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _result = this.getNextCompletedTask();
reply.writeNoException();
reply.writeString(_result);
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements edu.stanford.cs.gaming.sdk.service.GamingRemoteService
{
private android.os.IBinder mRemote;
Proxy(android.os.IBinder remote)
{
mRemote = remote;
}
public android.os.IBinder asBinder()
{
return mRemote;
}
public java.lang.String getInterfaceDescriptor()
{
return DESCRIPTOR;
}
public int getCounter() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
int _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getCounter, _data, _reply, 0);
_reply.readException();
_result = _reply.readInt();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
public java.lang.String getLocationString() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.lang.String _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getLocationString, _data, _reply, 0);
_reply.readException();
_result = _reply.readString();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
public boolean addApp(java.lang.String appName, java.lang.String appApiKey) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
boolean _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(appName);
_data.writeString(appApiKey);
mRemote.transact(Stub.TRANSACTION_addApp, _data, _reply, 0);
_reply.readException();
_result = (0!=_reply.readInt());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
public void doGet(java.lang.String url) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(url);
mRemote.transact(Stub.TRANSACTION_doGet, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
public void putGameObject(java.lang.String gameObjJsonStr) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(gameObjJsonStr);
mRemote.transact(Stub.TRANSACTION_putGameObject, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
public java.lang.String getNextCompletedTask() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.lang.String _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getNextCompletedTask, _data, _reply, 0);
_reply.readException();
_result = _reply.readString();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
}
static final int TRANSACTION_getCounter = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_getLocationString = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
static final int TRANSACTION_addApp = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);
static final int TRANSACTION_doGet = (android.os.IBinder.FIRST_CALL_TRANSACTION + 3);
static final int TRANSACTION_putGameObject = (android.os.IBinder.FIRST_CALL_TRANSACTION + 4);
static final int TRANSACTION_getNextCompletedTask = (android.os.IBinder.FIRST_CALL_TRANSACTION + 5);
}
public int getCounter() throws android.os.RemoteException;
public java.lang.String getLocationString() throws android.os.RemoteException;
public boolean addApp(java.lang.String appName, java.lang.String appApiKey) throws android.os.RemoteException;
public void doGet(java.lang.String url) throws android.os.RemoteException;
public void putGameObject(java.lang.String gameObjJsonStr) throws android.os.RemoteException;
public java.lang.String getNextCompletedTask() throws android.os.RemoteException;
}
