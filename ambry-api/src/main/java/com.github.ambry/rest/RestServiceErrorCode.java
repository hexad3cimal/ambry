package com.github.ambry.rest;

/**
 * All the error codes that accompany a {@link RestServiceException}. Each of these error codes are expected to go
 * into certain "groups" that map to HTTP error codes.
 * <p/>
 * The groups are:
 * {@link #BadRequest}
 * {@link #InternalServerError}
 * {@link #UnknownErrorCode}
 * <p/>
 * About logging:
 * Generally, error codes belonging to the group {@link #BadRequest} are logged at DEBUG level. Those that belong to
 * {@link #InternalServerError} or {@link #UnknownErrorCode} are logged at ERROR level.
 */
public enum RestServiceErrorCode {
  /**
   * Generic BadRequest error code when client provides a request that is not fit for processing.
   */
  BadRequest,
  /**
   * Client has supplied arguments that are not valid.
   */
  InvalidArgs,
  /**
   * Client has sent a request that is cannot be decoded using the REST protocol (usually HTTP).
   */
  MalformedRequest,
  /**
   * Client has sent a request that is missing some arguments necessary to service the request.
   */
  MissingArgs,
  /**
   * Client has sent request content without sending request metadata first.
   */
  NoRequest,
  /**
   * Client is requesting a HTTP method that is not supported.
   */
  UnsupportedHttpMethod,
  /**
   * Indicates that HttpObject received was not of a recognized type (Currently this is internal to Netty and this
   * error indicates that the received HttpObject was neither HttpRequest nor HttpContent).
   */
  UnknownHttpObject,
  /**
   * Client has requested for an operation that is not supported by the {@link BlobStorageService}.
   */
  UnsupportedOperation,

  /**
   * Generic InternalServerError that is a result of problems on the server side that is not caused by the client and
   * there is nothing that a client can do about it.
   */
  InternalServerError,
  /**
   * Indicates failure of tasks that needed to be done when a new channel with a client became active.
   */
  ChannelActiveTasksFailure,
  /**
   * Indicates that an operation is being performed upon a channel that has been closed already.
   */
  ChannelAlreadyClosed,
  /**
   * Indicates that a state transition while generating response metadata is invalid.
   */
  IllegalResponseMetadataStateTransition,
  /**
   * Indicates that there was a {@link InterruptedException} while trying to perform the operation.
   */
  OperationInterrupted,
  /**
   * Indicates failure of the {@link RestRequestHandlerController} to select and provide a {@link RestRequestHandler}.
   */
  RequestHandlerSelectionError,
  /**
   * Indicates failure of the {@link RestRequestHandler} to handle a submitted request.
   */
  RequestHandleFailure,
  /**
   * Indicates that the {@link RestRequestHandler} is unavailable for request handling. Thrown when the
   * {@link RestRequestHandler} is not started up or has died.
   */
  RequestHandlerUnavailable,
  /**
   * Indicates that the submitted {@link RestRequestInfo} could not be queued for handling in the
   * {@link RestRequestHandler}.
   */
  RestRequestInfoQueueingFailure,
  /**
   * Indicates that the submitted {@link RestRequestInfo} is null.
   */
  RestRequestInfoNull,
  /**
   * Indicates that there was a problem building the response (usually happens when the response is JSON).
   */
  ResponseBuildingFailure,
  /**
   * Indicates that there is no reference of a {@link RestResponseHandler} in the {@link RestRequestInfo}.
   */
  ResponseHandlerNull,
  /**
   * Indicates that there is no reference of a {@link RestRequestMetadata} in the {@link RestRequestInfo}.
   */
  RequestMetadataNull,
  /**
   * Indicates that an object that is needed for the request could not be created due to an internal server error.
   */
  InternalObjectCreationError,
  /**
   * Indicates a {@link RestMethod} is not supported by an implementation of {@link RestRequestHandler} (May
   * also indicate a bug where behaviour for a new {@link RestMethod} has not been defined in the implementation).
   */
  UnsupportedRestMethod,

  /**
   * Error code group that catches all RestServiceErrorCodes that are not defined as part of a group.
   */
  UnknownErrorCode;

  /**
   * Gets the error code group that a certain RestServiceErrorCode belongs to (mostly used for http error reporting
   * purposes).
   * @param code - the input RestServiceErrorCode.
   * @return - the group that the RestServiceErrorCode belongs to.
   */
  public static RestServiceErrorCode getErrorCodeGroup(RestServiceErrorCode code) {
    switch (code) {
      case BadRequest:
      case InvalidArgs:
      case MalformedRequest:
      case MissingArgs:
      case NoRequest:
      case UnknownHttpObject:
      case UnsupportedOperation:
      case UnsupportedHttpMethod:
        return BadRequest;
      case InternalServerError:
      case ChannelActiveTasksFailure:
      case ChannelAlreadyClosed:
      case IllegalResponseMetadataStateTransition:
      case OperationInterrupted:
      case RequestHandlerSelectionError:
      case RequestHandleFailure:
      case RequestHandlerUnavailable:
      case RestRequestInfoQueueingFailure:
      case RestRequestInfoNull:
      case ResponseBuildingFailure:
      case ResponseHandlerNull:
      case RequestMetadataNull:
      case InternalObjectCreationError:
      case UnsupportedRestMethod:
        return InternalServerError;
      default:
        return UnknownErrorCode;
    }
  }
}