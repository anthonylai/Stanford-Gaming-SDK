/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: /Users/james04321/android_dev/Stanford-Gaming-SDK/src/edu/stanford/cs/gaming/sdk/service/GamingRemoteService.aidl
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
int _arg0;
_arg0 = data.readInt();
java.lang.String _arg1;
_arg1 = data.readString();
java.lang.String _arg2;
_arg2 = data.readString();
boolean _result = this.addApp(_arg0, _arg1, _arg2);
reply.writeNoException();
reply.writeInt(((_result)?(1):(0)));
return true;
}
case TRANSACTION_setUserId:
{
data.enforceInterface(DESCRIPTOR);
int _arg0;
_arg0 = data.readInt();
int _arg1;
_arg1 = data.readInt();
long _arg2;
_arg2 = data.readLong();
java.lang.String _arg3;
_arg3 = data.readString();
boolean _result = this.setUserId(_arg0, _arg1, _arg2, _arg3);
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
case TRANSACTION_sendRequest:
{
data.enforceInterface(DESCRIPTOR);
int _arg0;
_arg0 = data.readInt();
java.lang.String _arg1;
_arg1 = data.readString();
boolean _result = this.sendRequest(_arg0, _arg1);
reply.writeNoException();
reply.writeInt(((_result)?(1):(0)));
return true;
}
case TRANSACTION_hasPendingNotification:
{
data.enforceInterface(DESCRIPTOR);
int _arg0;
_arg0 = data.readInt();
java.lang.String _arg1;
_arg1 = data.readString();
boolean _result = this.hasPendingNotification(_arg0, _arg1);
reply.writeNoException();
reply.writeInt(((_result)?(1):(0)));
return true;
}
case TRANSACTION_getNextPendingNotification:
{
data.enforceInterface(DESCRIPTOR);
int _arg0;
_arg0 = data.readInt();
java.lang.String _arg1;
_arg1 = data.readString();
java.lang.String _result = this.getNextPendingNotification(_arg0, _arg1);
reply.writeNoException();
reply.writeString(_result);
return true;
}
case TRANSACTION_getLastConciergeId:
{
data.enforceInterface(DESCRIPTOR);
int _result = this.getLastConciergeId();
reply.writeNoException();
reply.writeInt(_result);
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
public boolean addApp(int appId, java.lang.String appApiKey, java.lang.String intentFilterEvent) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
boolean _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(appId);
_data.writeString(appApiKey);
_data.writeString(intentFilterEvent);
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
public boolean setUserId(int appId, int userId, long fbId, java.lang.String token) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
boolean _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(appId);
_data.writeInt(userId);
_data.writeLong(fbId);
_data.writeString(token);
mRemote.transact(Stub.TRANSACTION_setUserId, _data, _reply, 0);
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
//	void putGameObject(String gameObjJsonStr);
//	String getNextCompletedTask(int appId);
//    boolean removeApp(int appId);	

public boolean sendRequest(int appId, java.lang.String request) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
boolean _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(appId);
_data.writeString(request);
mRemote.transact(Stub.TRANSACTION_sendRequest, _data, _reply, 0);
_reply.readException();
_result = (0!=_reply.readInt());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
public boolean hasPendingNotification(int appId, java.lang.String intentFilterEvent) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
boolean _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(appId);
_data.writeString(intentFilterEvent);
mRemote.transact(Stub.TRANSACTION_hasPendingNotification, _data, _reply, 0);
_reply.readException();
_result = (0!=_reply.readInt());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
public java.lang.String getNextPendingNotification(int appId, java.lang.String intentFilterEvent) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.lang.String _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(appId);
_data.writeString(intentFilterEvent);
mRemote.transact(Stub.TRANSACTION_getNextPendingNotification, _data, _reply, 0);
_reply.readException();
_result = _reply.readString();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
public int getLastConciergeId() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
int _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getLastConciergeId, _data, _reply, 0);
_reply.readException();
_result = _reply.readInt();
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
static final int TRANSACTION_setUserId = (android.os.IBinder.FIRST_CALL_TRANSACTION + 3);
static final int TRANSACTION_doGet = (android.os.IBinder.FIRST_CALL_TRANSACTION + 4);
static final int TRANSACTION_sendRequest = (android.os.IBinder.FIRST_CALL_TRANSACTION + 5);
static final int TRANSACTION_hasPendingNotification = (android.os.IBinder.FIRST_CALL_TRANSACTION + 6);
static final int TRANSACTION_getNextPendingNotification = (android.os.IBinder.FIRST_CALL_TRANSACTION + 7);
static final int TRANSACTION_getLastConciergeId = (android.os.IBinder.FIRST_CALL_TRANSACTION + 8);
}
public int getCounter() throws android.os.RemoteException;
public java.lang.String getLocationString() throws android.os.RemoteException;
public boolean addApp(int appId, java.lang.String appApiKey, java.lang.String intentFilterEvent) throws android.os.RemoteException;
public boolean setUserId(int appId, int userId, long fbId, java.lang.String token) throws android.os.RemoteException;
public void doGet(java.lang.String url) throws android.os.RemoteException;
//	void putGameObject(String gameObjJsonStr);
//	String getNextCompletedTask(int appId);
//    boolean removeApp(int appId);	

public boolean sendRequest(int appId, java.lang.String request) throws android.os.RemoteException;
public boolean hasPendingNotification(int appId, java.lang.String intentFilterEvent) throws android.os.RemoteException;
public java.lang.String getNextPendingNotification(int appId, java.lang.String intentFilterEvent) throws android.os.RemoteException;
public int getLastConciergeId() throws android.os.RemoteException;
}
