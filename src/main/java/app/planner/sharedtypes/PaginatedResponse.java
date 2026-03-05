package app.planner.sharedtypes;

public record PaginatedResponse<T>(
    T content,
    int page,
    int size,
    int totalElements
) { }
