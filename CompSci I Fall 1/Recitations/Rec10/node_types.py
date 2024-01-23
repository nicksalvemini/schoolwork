"""
CSCI-141 Computer Science 1 Presentation Code
10-StacksQueues
Node

This is the definition of a singly linked node structure (frozen and unfrozen)
that will be used as the internal representation of our stack (frozen) and
queue (unfrozen)
structures.
"""

from dataclasses import dataclass
from typing import Any, Union

@dataclass(frozen=True)
class FrozenNode:
    value: Any
    next: Union[None, 'FrozenNode']

@dataclass(frozen=False)
class MutableNode:
    value: Any
    next: Union[None, 'MutableNode']